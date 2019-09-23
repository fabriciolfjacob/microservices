package br.com.curso.cadastroclientes.domain.repositories

import br.com.curso.cadastroclientes.domain.model.PessoaJuridica
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PessoaJuridicaRepository: JpaRepository<PessoaJuridica, Long>{

    @Query("select p from PessoaJuridica p where p.cnpj = :parcnpj")
    fun findByCnpj(@Param("parcnpj") cnpj: String): PessoaJuridica?
}