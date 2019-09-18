package br.com.curso.cadastroclientes.domain.services

import br.com.curso.cadastroclientes.domain.model.PessoaJuridica

interface PessoaJuridicaService {

    fun salvar(pessoaJuridica: PessoaJuridica): PessoaJuridica
}