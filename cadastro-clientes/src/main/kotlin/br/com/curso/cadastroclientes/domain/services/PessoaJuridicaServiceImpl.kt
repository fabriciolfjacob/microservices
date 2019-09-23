package br.com.curso.cadastroclientes.domain.services

import br.com.curso.cadastroclientes.domain.dto.PessoaDto
import br.com.curso.cadastroclientes.domain.exceptions.ObjetoDuplicadoException
import br.com.curso.cadastroclientes.domain.exceptions.ObjetoNaoEncontradoException
import br.com.curso.cadastroclientes.domain.model.Entidade
import br.com.curso.cadastroclientes.domain.model.PessoaFactory
import br.com.curso.cadastroclientes.domain.model.PessoaJuridica
import br.com.curso.cadastroclientes.domain.repositories.PessoaJuridicaRepository
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service

@Service
class PessoaJuridicaServiceImpl: PessoaJuridicaService {

    @Autowired
    lateinit var pessoaJuridicaRepository: PessoaJuridicaRepository

    @Autowired
    lateinit var cacheManager: CacheManager

    override fun salvar(pessoaDto: PessoaDto): PessoaJuridica {
        var pessoa = createPessoa(pessoaDto) as PessoaJuridica

        if(isCnpjExistente(pessoa.cnpj)){
            throw ObjetoDuplicadoException("Cnpj ja encontra-se cadastrado. Cnpj informado: ${pessoa.cnpj}")
        }

        return pessoaJuridicaRepository.save(pessoa)
    }

    override fun findById(id: Long): PessoaJuridica {
        var obj = pessoaJuridicaRepository.findById(id)

        if(!obj.isPresent){
            throw ObjetoNaoEncontradoException("Pessoa Juridica não encontrada. Pessoa informada: $id")
        }

        return obj.get()
    }

    @CachePut("pessoasJuridicas")
    @HystrixCommand(fallbackMethod = "findAllCache")
    override fun findAll(): List<PessoaJuridica> {
        return pessoaJuridicaRepository.findAll()
    }

    override fun update(id: Long, pessoaDto: PessoaDto): PessoaJuridica {
        var pessoa = findById(id)
        pessoa.nome = pessoaDto.nome
        pessoa.endereco = pessoaDto.endereco
        pessoa.cnpj = pessoaDto.documento
        return pessoaJuridicaRepository.save(pessoa)
    }

    override fun remove(id: Long) {
        var pessoa = findById(id)
        pessoaJuridicaRepository.delete(pessoa)
    }

    private fun isCnpjExistente(cnpj: String): Boolean {
        try{
            var pessoa = pessoaJuridicaRepository.findByCnpj(cnpj)

            if(pessoa != null){
                return true
            }

            return false
        }catch (e: Exception){
            return false
        }
    }

    private fun findAllCache(): List<PessoaJuridica> {
        return  emptyList()
    }

    private fun createPessoa(dto: PessoaDto): Entidade {
        var factory = PessoaFactory()
        return factory.getPessoa(dto.nome, dto.endereco, dto.documento)
    }
}