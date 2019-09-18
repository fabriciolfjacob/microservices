package br.com.curso.cadastroclientes.domain.exceptions

import java.io.Serializable

class PessoaIndefinidaException: RuntimeException, Serializable {

    constructor(msg: String, causa: Throwable): super(msg, causa)
    constructor(msg: String) : super(msg)

    companion object {
        private const val serialVersionUID = 1L
    }
}