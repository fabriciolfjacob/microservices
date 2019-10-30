package br.com.curso.recomendacaoproduto.domain.dto

import javax.validation.constraints.NotBlank

data class RecomendacaoDto(
        @NotBlank(message = "Informar a descrição da recomendacao.")
        var descricao: String,
        @NotBlank(message = "Informar o codigo do produto.")
        var codeProduto: String
)