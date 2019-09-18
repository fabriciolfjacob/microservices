package br.com.curso.cadastroclientes.domain.exceptions

import java.io.Serializable

class ObjetoDuplicadoException: RuntimeException, Serializable {

    constructor(msg: String): super(msg)
    constructor(msg: String, cause: Throwable): super(msg, cause)

    companion object {
        private const val serialVersionUID = 1L
    }
}