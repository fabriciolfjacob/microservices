package br.com.curso.pedido.domain.services

import br.com.curso.pedido.domain.dto.ProdutoDto
import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

/*old*/
/*@FeignClient(url = "http://localhost:8082", name = "produtos")
@RibbonClient(name = "produtos")
interface ProdutoService {

    @GetMapping("/{descricao}")
    fun getProduto(@PathVariable("descricao") descricao: String): ProdutoDto
}*/

@FeignClient(name = "api")
@RibbonClient(name = "produtos")
interface ProdutoService {

    @GetMapping("/produtos/{descricao}")
    fun getProduto(@PathVariable("descricao") descricao: String): ProdutoDto
}