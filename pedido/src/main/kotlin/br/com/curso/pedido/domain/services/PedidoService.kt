package br.com.curso.pedido.domain.services

import br.com.curso.pedido.domain.Pedido
import br.com.curso.pedido.domain.dto.PedidoDto

interface PedidoService {

    fun findById(id: String): Pedido
    fun findAll(): List<Pedido>
    fun deleteById(id: String)
    fun update(dto: PedidoDto, id: String)
    fun create(dto: PedidoDto): Pedido
}