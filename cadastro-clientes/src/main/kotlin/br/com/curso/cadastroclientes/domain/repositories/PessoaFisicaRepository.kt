package br.com.curso.cadastroclientes.domain.repositories

import br.com.curso.cadastroclientes.domain.model.PessoaFisica
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PessoaFisicaRepository: JpaRepository<PessoaFisica, Long>{

    @Query("select p from PessoaFisica p where p.cpf = :parcnpj")
    fun findByCpf(@Param("parcnpj") cpf: String): PessoaFisica
}