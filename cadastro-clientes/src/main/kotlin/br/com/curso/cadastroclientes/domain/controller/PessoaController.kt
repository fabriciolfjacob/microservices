package br.com.curso.cadastroclientes.domain.controller

import br.com.curso.cadastroclientes.domain.dto.PessoaDto
import br.com.curso.cadastroclientes.domain.model.PessoaFisica
import br.com.curso.cadastroclientes.domain.services.PessoaFisicaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/pessoa")
@CrossOrigin(origins = arrayOf("*"))
class PessoaController {

    @Autowired
    lateinit var pessoaFisicaService: PessoaFisicaService

    @PostMapping("/pf")
    fun createPf(@RequestBody dto: PessoaDto): ResponseEntity<Unit>{
        var pessoa = pessoaFisicaService.salvar(dto)
        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pessoa.id)
                .toUri()
        return ResponseEntity.created(uri).build()
    }

    @GetMapping("/pf/{id}")
    fun findByIdPf(@PathVariable("id") id: Long): ResponseEntity<PessoaFisica>{
        return ResponseEntity.ok().body(pessoaFisicaService.findById(id))
    }
}