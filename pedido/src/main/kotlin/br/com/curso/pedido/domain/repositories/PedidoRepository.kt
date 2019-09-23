package br.com.curso.pedido.domain.repositories

import br.com.curso.pedido.domain.Pedido
import org.springframework.data.mongodb.repository.MongoRepository

interface PedidoRepository: MongoRepository<Pedido, String>