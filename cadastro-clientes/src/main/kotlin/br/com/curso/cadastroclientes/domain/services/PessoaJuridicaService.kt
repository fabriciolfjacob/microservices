package br.com.curso.cadastroclientes.domain.services

import br.com.curso.cadastroclientes.domain.dto.PessoaDto
import br.com.curso.cadastroclientes.domain.model.PessoaJuridica

interface PessoaJuridicaService {

    fun salvar(pessoaDto: PessoaDto): PessoaJuridica
    fun update(id: Long, pessoaDto: PessoaDto): PessoaJuridica
    fun remove(id: Long)
    fun findById(id: Long): PessoaJuridica
    fun findAll(): List<PessoaJuridica>
}