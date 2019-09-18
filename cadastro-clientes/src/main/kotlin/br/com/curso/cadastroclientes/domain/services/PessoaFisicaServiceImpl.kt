package br.com.curso.cadastroclientes.domain.services

import br.com.curso.cadastroclientes.domain.dto.PessoaDto
import br.com.curso.cadastroclientes.domain.exceptions.ObjetoNaoEncontradoException
import br.com.curso.cadastroclientes.domain.model.*
import br.com.curso.cadastroclientes.domain.repositories.PessoaFisicaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PessoaFisicaServiceImpl: PessoaFisicaService {

    @Autowired
    lateinit var pessoaFisicaRepository: PessoaFisicaRepository

    override fun salvar(dto: PessoaDto): PessoaFisica {
        var pessoa = createPessoa(dto) as PessoaFisica
        return pessoaFisicaRepository.save(pessoa)
    }

    override fun findById(id: Long): PessoaFisica {
        var obj = pessoaFisicaRepository.findById(id)

        if(!obj.isPresent){
            throw ObjetoNaoEncontradoException("Pessoa não localizada. Id informado: $id")
        }

        return obj.get()
    }

    private fun createPessoa(dto: PessoaDto): Entidade {
        var factory = PessoaFactory()
        return factory.getPessoa(dto.nome, dto.endereco, dto.documento)
    }
}