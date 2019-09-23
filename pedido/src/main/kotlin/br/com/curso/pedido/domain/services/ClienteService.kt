package br.com.curso.pedido.domain.services

import br.com.curso.pedido.domain.dto.PessoaFisicaDto
import br.com.curso.pedido.domain.dto.PessoaJuridicaDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name="cadastro-clientes")
interface ClienteService {

    @GetMapping("/pf/{cpf}")
    fun findByCnpj(@PathVariable("cpf") cnpj: String): PessoaJuridicaDto

    @GetMapping("/pj/{cnpj}")
    fun findByCpf(@PathVariable("cnpj") cpf: String): PessoaFisicaDto
}