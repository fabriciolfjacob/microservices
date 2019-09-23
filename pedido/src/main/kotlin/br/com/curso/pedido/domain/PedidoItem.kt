package br.com.curso.pedido.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document(collection = "pedidoItens")
data class PedidoItem(
        @Id
        var id: String?,
        var idProduto: String,
        var quantidade: Int,
        var total: BigDecimal
)