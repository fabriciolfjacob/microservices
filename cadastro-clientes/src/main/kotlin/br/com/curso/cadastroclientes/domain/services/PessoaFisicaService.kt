package br.com.curso.cadastroclientes.domain.services

import br.com.curso.cadastroclientes.domain.dto.PessoaDto
import br.com.curso.cadastroclientes.domain.model.PessoaFisica

interface PessoaFisicaService {

    fun salvar(dto: PessoaDto): PessoaFisica
    fun findById(id: Long): PessoaFisica
}