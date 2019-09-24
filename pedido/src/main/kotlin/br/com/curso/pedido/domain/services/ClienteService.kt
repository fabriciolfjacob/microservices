package br.com.curso.pedido.domain.services

import br.com.curso.pedido.domain.dto.PessoaFisicaDto
import br.com.curso.pedido.domain.dto.PessoaJuridicaDto
import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(url = "http://localhost:8200", name="clientes")
@RibbonClient(name = "clientes")
interface ClienteService {

    @GetMapping("/pj/{cnpj}")
    fun findByCnpj(@PathVariable("cnpj") cnpj: String): PessoaJuridicaDto

    @GetMapping("/pf/{cpf}")
    fun findByCpf(@PathVariable("cpf") cpf: String): PessoaFisicaDto
}