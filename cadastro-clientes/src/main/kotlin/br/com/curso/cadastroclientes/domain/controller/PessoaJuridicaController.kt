package br.com.curso.cadastroclientes.domain.controller

import br.com.curso.cadastroclientes.domain.dto.PessoaDto
import br.com.curso.cadastroclientes.domain.model.PessoaJuridica
import br.com.curso.cadastroclientes.domain.services.PessoaJuridicaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/pj")
@CrossOrigin(origins = arrayOf("*"))
class PessoaJuridicaController{

    @Autowired
    lateinit var pessoaJuridicaService: PessoaJuridicaService

    @PostMapping
    fun createPj(@RequestBody dto: PessoaDto): ResponseEntity<Unit> {
        var pessoa = pessoaJuridicaService.salvar(dto)
        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pessoa.id)
                .toUri()
        return ResponseEntity.created(uri).build()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long) : ResponseEntity<PessoaJuridica> {
        return ResponseEntity.ok().body(pessoaJuridicaService.findById(id))
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<PessoaJuridica>> {
        return ResponseEntity.ok().body(pessoaJuridicaService.findAll())
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id")id: Long): ResponseEntity<Unit>{
        pessoaJuridicaService.remove(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{id}")
    fun updatePf(@PathVariable("id")id : Long, @RequestBody pessoaDto: PessoaDto): ResponseEntity<PessoaJuridica>{
        return ResponseEntity.ok().body(pessoaJuridicaService.update(id, pessoaDto))
    }
}