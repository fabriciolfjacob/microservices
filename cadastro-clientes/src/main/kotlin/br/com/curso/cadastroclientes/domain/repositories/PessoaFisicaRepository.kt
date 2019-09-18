package br.com.curso.cadastroclientes.domain.repositories

import br.com.curso.cadastroclientes.domain.model.PessoaFisica
import org.springframework.data.jpa.repository.JpaRepository

interface PessoaFisicaRepository: JpaRepository<PessoaFisica, Long>