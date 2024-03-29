package br.com.curso.cadastroclientes.domain.model

import javax.persistence.*

@Entity
@DiscriminatorValue(value = "PF")
data class PessoaFisica(override var nome: String, override var endereco: String, @Column(name = "cpf", unique = true) var cpf: String): Pessoa(), Entidade {

    constructor(): this("", "", "")
}