package br.com.curso.cadastroclientes.domain.model

import java.io.Serializable
import javax.persistence.*
import javax.persistence.GenerationType
import javax.persistence.GeneratedValue
import javax.persistence.DiscriminatorColumn
import javax.persistence.Inheritance


@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
open class Pessoa(): Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Column(name = "nome")
    open var nome: String = ""
    @Column(name ="endereco")
    open var endereco: String = ""
}


