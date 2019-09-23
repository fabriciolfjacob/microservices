package br.com.curso.pedido.domain.controller

import br.com.curso.pedido.domain.dto.PedidoDto
import br.com.curso.pedido.domain.services.PedidoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = arrayOf("*"))
class PedidoController {

    @Autowired
    lateinit var pedidoService: PedidoService

    @PostMapping
    fun create(@Valid @RequestBody dto: PedidoDto): ResponseEntity<Unit>{
        var pedido = pedidoService.create(dto)
        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pedido.id)
                .toUri()
        return ResponseEntity.created(uri).build()
    }
}