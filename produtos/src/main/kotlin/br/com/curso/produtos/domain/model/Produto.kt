package br.com.curso.produtos.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document(collection = "produtos")
data class Produto(
        @Id
        var id: String?,
        var descricao: String,
        var preco: BigDecimal,
        var port: Int
)