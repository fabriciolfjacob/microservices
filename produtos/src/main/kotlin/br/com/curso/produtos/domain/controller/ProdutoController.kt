package br.com.curso.produtos.domain.controller

import br.com.curso.produtos.domain.dto.ProdutoDto
import br.com.curso.produtos.domain.model.Produto
import br.com.curso.produtos.domain.service.ProdutoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = arrayOf("*"))
class ProdutoController{

    @Autowired
    lateinit var produtoService: ProdutoService

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<Produto>{
        return produtoService.findAll()
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun findById(@PathVariable("id") id: String): Produto{
        return produtoService.findById(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody @Valid dto: ProdutoDto, @PathVariable("id") id: String){
        produtoService.update(dto, id)
    }

    @PostMapping
    fun create(@RequestBody @Valid dto: ProdutoDto): ResponseEntity<Unit>{
        var produto = produtoService.salvar(dto)
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produto.id)
                .toUri()
        return ResponseEntity.created(uri).build()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable("id") id: String){
        produtoService.delete(id)
    }

}