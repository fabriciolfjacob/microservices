package br.com.curso.pedido.domain.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class PedidoDto(
        var cpf: String?,
        var cnpj: String?,
        @NotEmpty
        var pedidoItem: List<PedidoItemDto>
)

data class PedidoItemDto(
        @NotBlank
        var descricaoProduto: String,
        var quantidade: Int
)