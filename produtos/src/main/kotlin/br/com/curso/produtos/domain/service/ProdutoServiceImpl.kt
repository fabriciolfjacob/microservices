package br.com.curso.produtos.domain.service

import br.com.curso.produtos.domain.dto.ProdutoDto
import br.com.curso.produtos.domain.exceptions.ObjectNotFoundException
import br.com.curso.produtos.domain.exceptions.ProdutoDuplicadoException
import br.com.curso.produtos.domain.exceptions.ProdutoPrecoZeroException
import br.com.curso.produtos.domain.model.Produto
import br.com.curso.produtos.domain.repositories.ProdutoRepository
import com.netflix.discovery.converters.Auto
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachePut
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ProdutoServiceImpl: ProdutoService {

    @Autowired
    lateinit var produtoRepository: ProdutoRepository

    @Autowired
    lateinit var env: Environment

    @Autowired
    lateinit var cacheManager: CacheManager

    private val logger = LoggerFactory.getLogger(ProdutoServiceImpl::class.java)

    override fun salvar(dto: ProdutoDto): Produto {
        logger.info("Salvando o produto")
        validarDto(dto, true)
        var produto = converterDtoToProduto(dto)
        return produtoRepository.save(produto)
    }

    private fun converterDtoToProduto(dto: ProdutoDto): Produto {
        return Produto(null, dto.descricao, dto.preco,Integer.parseInt(env.getProperty("local.server.port")))
    }

    private fun validarDto(dto: ProdutoDto, isCreated: Boolean = false) {
        logger.info("Validando o produto.")
        if(dto.preco == BigDecimal(0)){
            throw ProdutoPrecoZeroException("Produto com valor zerado.")
        }

        if(isCreated) {
            var produto = buscarProdutoPorId(dto.descricao)

            if (produto != null) {
                throw ProdutoDuplicadoException("Produto já encontra-se cadastrado. Descricão informada: ${dto.descricao}")
            }
        }
    }

    override fun update(dto: ProdutoDto, id: String) {
        logger.info("Atualizando o produto. Id: $id")
        validarDto(dto)
        var produto = getProduto(id)
        produto.descricao = dto.descricao
        produto.preco = dto.preco
        produtoRepository.save(produto)
    }

    private fun getProduto(id: String): Produto {
        var produto = buscarProdutoPorId(id)
        produto ?: throw ObjectNotFoundException("Produto não encontrado.")
        return produto!!
    }

    private fun buscarProdutoPorId(id: String): Produto?{
        try{
            return produtoRepository.findById(id).get()
        }catch (e: Exception){
            return null
        }
    }

    override fun delete(id: String) {
        var produto = getProduto(id)
        produtoRepository.delete(produto)
    }

    @HystrixCommand(fallbackMethod = "findByIdCache")
    @CachePut("produtos")
    override fun findById(id: String): Produto {
        logger.info("Pesquisando produto de id: $id")
        /*var random = Random()
        if(random.nextInt(26) % 2 == 0) {
            throw RuntimeException("teste")
        }*/
        return getProduto(id)
    }

    override fun findAll(): List<Produto> {
        var produtos = produtoRepository.findAll()

        if(produtos.isEmpty()){
            throw ObjectNotFoundException("Nenhum produto cadastrado.")
        }

        return produtos
    }

    fun findByIdCache(id: String): Produto?{
        logger.warn("Iniciando método de fallback.")
        var value = cacheManager.getCache("produtos")!!.get(id)

        if(value != null){
            return value.get() as Produto
        }

        return null
    }
}