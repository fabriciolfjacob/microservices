package br.com.curso.cadastroclientes.domain.exceptions

import java.lang.RuntimeException

class ObjetoNaoEncontradoException: RuntimeException {

    constructor(msg: String, cause: Throwable): super(msg, cause)
    constructor(msg: String): super(msg)
}