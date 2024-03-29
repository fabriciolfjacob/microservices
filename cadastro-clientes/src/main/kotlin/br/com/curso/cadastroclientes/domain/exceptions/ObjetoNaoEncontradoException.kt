package br.com.curso.cadastroclientes.domain.exceptions

import java.io.Serializable

class ObjetoNaoEncontradoException: RuntimeException, Serializable {

    constructor(msg: String, cause: Throwable): super(msg, cause)
    constructor(msg: String): super(msg)

    companion object {
        private const val serialVersionUID = 1L
    }
}