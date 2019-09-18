package br.com.curso.cadastroclientes.domain.repositories

import br.com.curso.cadastroclientes.domain.model.PessoaJuridica
import org.springframework.data.jpa.repository.JpaRepository

interface PessoaJuridicaRepository: JpaRepository<PessoaJuridica, Long>