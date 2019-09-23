package br.com.curso.cadastroclientes.domain.controller

import br.com.curso.cadastroclientes.domain.dto.PessoaDto
import br.com.curso.cadastroclientes.domain.model.PessoaFisica
import br.com.curso.cadastroclientes.domain.services.PessoaFisicaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/pf")
@CrossOrigin(origins = arrayOf("*"))
class PessoaFisicaController {

    @Autowired
    lateinit var pessoaFisicaService: PessoaFisicaService

    @PostMapping
    fun createPf(@RequestBody dto: PessoaDto): ResponseEntity<Unit>{
        var pessoa = pessoaFisicaService.salvar(dto)
        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pessoa.id)
                .toUri()
        return ResponseEntity.created(uri).build()
    }

    @GetMapping("/{cpf}")
    fun findByIdPf(@PathVariable("cpf") cpf: String): ResponseEntity<PessoaFisica>{
        return ResponseEntity.ok().body(pessoaFisicaService.findByCpf(cpf))
    }

    @PutMapping("/{id}")
    fun updatePf(@PathVariable("id")id : Long, @RequestBody pessoaDto: PessoaDto): ResponseEntity<PessoaFisica>{
        return ResponseEntity.ok().body(pessoaFisicaService.update(id, pessoaDto))
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<PessoaFisica>>{
        return ResponseEntity.ok().body(pessoaFisicaService.findAll())
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id")id: Long): ResponseEntity<Unit>{
        pessoaFisicaService.remove(id)
        return ResponseEntity.noContent().build()
    }
}