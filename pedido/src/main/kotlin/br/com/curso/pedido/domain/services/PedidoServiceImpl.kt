package br.com.curso.pedido.domain.services

import br.com.curso.pedido.domain.Pedido
import br.com.curso.pedido.domain.PedidoItem
import br.com.curso.pedido.domain.dto.PedidoDto
import br.com.curso.pedido.domain.exceptions.ClienteNaoInformadoException
import br.com.curso.pedido.domain.exceptions.PedidoNaoLocalizadoException
import br.com.curso.pedido.domain.exceptions.PedidoSemProdutosException
import br.com.curso.pedido.domain.repositories.PedidoItemRepository
import br.com.curso.pedido.domain.repositories.PedidoRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class PedidoServiceImpl: PedidoService {

    private val logger = LoggerFactory.getLogger(PedidoServiceImpl::class.java)

    @Autowired
    lateinit var pedidoRepository: PedidoRepository

    @Autowired
    lateinit var produtoService: ProdutoService

    @Autowired
    lateinit var clienteService: ClienteService

    @Autowired
    lateinit var pedidoItemRepository: PedidoItemRepository

    override fun findById(id: String): Pedido {
       try{
           return pedidoRepository.findById(id).get()
       }catch (e: Exception){
           throw PedidoNaoLocalizadoException("Pedido não localizado. Id informado : $id")
       }
    }

    override fun findAll(): List<Pedido> {
        return pedidoRepository.findAll()
    }

    override fun deleteById(id: String) {
        var pedido = findById(id)
        pedidoRepository.delete(pedido)
    }

    override fun update(dto: PedidoDto, id: String) {
        validarProduto(dto)
        var pedido = findById(id)
        var produtos = setProdutos(dto)
        pedido.itens = produtos
        pedido.total = getTotalPedido(produtos)

        pedidoRepository.save(pedido)
    }

    override fun create(dto: PedidoDto): Pedido {
        validarProduto(dto)

        var produtos = setProdutos(dto)
        var pedido = Pedido.PedidoBuilder().addCliente(setCliente(dto))
                .addItens(produtos)
                .addTotal(getTotalPedido(produtos)).build()

        return pedidoRepository.save(pedido)
    }

    private fun getTotalPedido(pedidoItens: List<PedidoItem>): BigDecimal{
        var total = pedidoItens.sumByDouble { it.total.toDouble() }
        return BigDecimal(total).setScale(4, RoundingMode.HALF_EVEN)
    }

    private fun validarProduto(dto: PedidoDto) {
        if(dto.pedidoItem == null || dto.pedidoItem.isEmpty()){
            throw PedidoSemProdutosException("Nenhum produto informado no pedido.")
        }

        if(dto.cnpj == null && dto.cpf == null){
            throw ClienteNaoInformadoException("Nenhum cliente informado.")
        }
    }

    private fun setProdutos(dto: PedidoDto): List<PedidoItem>{
        var produtos = dto.pedidoItem.map {
            var produtoDto = produtoService.getProduto(it.descricaoProduto)
            logger.info("Porta da aplicação : ${produtoDto.port}")
            PedidoItem(null, produtoDto.id, it.quantidade, produtoDto.preco.multiply(BigDecimal( it.quantidade)))
        }

        produtos.map { pedidoItemRepository.save(it) }
        return  produtos
    }

    private fun setCliente(dto: PedidoDto): Long {
        if(dto.cpf != null){
            var cliente = clienteService.findByCpf(dto.cpf!!)
            return cliente.id
        }

        var cliente = clienteService.findByCnpj(dto.cnpj!!)
        return cliente.id
    }
}