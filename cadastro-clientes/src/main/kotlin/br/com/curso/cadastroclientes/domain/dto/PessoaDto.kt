package br.com.curso.cadastroclientes.domain.dto

import javax.validation.constraints.NotBlank

data class PessoaDto(
        @NotBlank(message = "Informa o nome da pessoa")
        val nome: String,
        @NotBlank(message = "Informa o endereço da pessoa")
        val endereco: String,
        @NotBlank(message = "Informa o cpf da pessoa")
        val documento: String)