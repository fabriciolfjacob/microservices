package br.com.curso.cadastroclientes.domain.model

import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("PJ")
class PessoaJuridica(override var nome: String, override var endereco: String, @Column(name = "cnpj", unique = true) var cnpj: String): Pessoa(), Entidade{
    constructor(): this("", "", "")
}