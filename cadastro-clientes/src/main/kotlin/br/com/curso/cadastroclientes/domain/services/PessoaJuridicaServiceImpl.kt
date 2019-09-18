package br.com.curso.cadastroclientes.domain.services

import br.com.curso.cadastroclientes.domain.model.PessoaJuridica
import br.com.curso.cadastroclientes.domain.repositories.PessoaJuridicaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PessoaJuridicaServiceImpl: PessoaJuridicaService {

    @Autowired
    lateinit var pessoaJuridicaRepository: PessoaJuridicaRepository

    override fun salvar(pessoaJuridica: PessoaJuridica): PessoaJuridica {
        return pessoaJuridicaRepository.save(pessoaJuridica)
    }
}