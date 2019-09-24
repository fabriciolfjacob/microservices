package br.com.curso.pedido.domain.repositories

import br.com.curso.pedido.domain.PedidoItem
import org.springframework.data.mongodb.repository.MongoRepository

interface PedidoItemRepository: MongoRepository<PedidoItem, String> {
}