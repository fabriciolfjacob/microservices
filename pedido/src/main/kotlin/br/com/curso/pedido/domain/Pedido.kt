package br.com.curso.pedido.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import javax.validation.constraints.NotBlank

@Document(collection = "pedidos")
data class Pedido(
        @Id
        var id: String?,
        @NotBlank
        var idCliente: Long,
        var total: BigDecimal,
        @DBRef(lazy = true)
        var itens: List<PedidoItem>){

    data class PedidoBuilder(
        var idCliente: Long = 0L,
        var total: BigDecimal = BigDecimal(0),
        var itens : List<PedidoItem> = emptyList()){

        fun addCliente(id: Long) = apply { this.idCliente = id }
        fun addTotal(total: BigDecimal) = apply { this.total = total }
        fun addItens(itens: List<PedidoItem>) = apply { this.itens = itens }

        fun build() = Pedido(
                null, idCliente, total, itens
        )
    }


}