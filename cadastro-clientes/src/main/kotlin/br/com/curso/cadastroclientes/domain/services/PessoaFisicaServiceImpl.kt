package br.com.curso.cadastroclientes.domain.services

import br.com.curso.cadastroclientes.domain.dto.PessoaDto
import br.com.curso.cadastroclientes.domain.exceptions.ObjetoDuplicadoException
import br.com.curso.cadastroclientes.domain.exceptions.ObjetoNaoEncontradoException
import br.com.curso.cadastroclientes.domain.model.*
import br.com.curso.cadastroclientes.domain.repositories.PessoaFisicaRepository
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service

@Service
class PessoaFisicaServiceImpl: PessoaFisicaService {

    @Autowired
    lateinit var pessoaFisicaRepository: PessoaFisicaRepository

    @Autowired
    lateinit var cacheManager: CacheManager

    override fun salvar(dto: PessoaDto): PessoaFisica {
        var pessoa = createPessoa(dto) as PessoaFisica

        if (isCpfExistente(pessoa.cpf)) {
            throw ObjetoDuplicadoException("Cpf ja cadastrado. Cpf informado: ${pessoa.cpf}")
        }

        return pessoaFisicaRepository.save(pessoa)
    }

    private fun isCpfExistente(cpf: String): Boolean {
        try{
            var pessoa = pessoaFisicaRepository.findByCpf(cpf)

            if(pessoa != null){
                return true
            }

            return false
        }catch (e: Exception){
            return false
        }
    }

    override fun findById(id: Long): PessoaFisica {
        var obj = pessoaFisicaRepository.findById(id)

        if(!obj.isPresent){
            throw ObjetoNaoEncontradoException("Pessoa não localizada. Id informado: $id")
        }

        return obj.get()
    }

    @HystrixCommand(fallbackMethod = "findAllCache")
    @CachePut("pessoasFisicas")
    override fun findAll(): List<PessoaFisica> {
        return pessoaFisicaRepository.findAll()
    }

    override fun update(id: Long, pessoaDto: PessoaDto): PessoaFisica {
        var pessoa = findById(id)
        pessoa.nome = pessoaDto.nome
        pessoa.endereco = pessoaDto.endereco
        pessoa.cpf = pessoaDto.documento
        return pessoaFisicaRepository.save(pessoa)
    }

    override fun remove(id: Long) {
        var pessoa = findById(id)
        pessoaFisicaRepository.delete(pessoa)
    }

    private fun findAllCache(): List<PessoaFisica>{
        return  emptyList()
    }

    private fun createPessoa(dto: PessoaDto): Entidade {
        var factory = PessoaFactory()
        return factory.getPessoa(dto.nome, dto.endereco, dto.documento)
    }
}