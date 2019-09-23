package br.com.curso.produtos.domain.dto

import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

data class ProdutoDto(
        @NotBlank(message = "Informe o nome do produto.")
        var descricao: String,
        @NotBlank(message = "Informe o valor do produto.")
        @Positive(message = "Informe um valor acima de zero.")
        var preco: BigDecimal
)