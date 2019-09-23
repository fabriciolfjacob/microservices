package br.com.curso.pedido.domain.dto

import java.math.BigDecimal

data class ProdutoDto(
        var id: String,
        var preco: BigDecimal,
        var port: Int
)