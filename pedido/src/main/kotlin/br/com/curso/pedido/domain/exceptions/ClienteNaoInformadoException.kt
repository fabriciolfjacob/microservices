package br.com.curso.pedido.domain.exceptions

import java.lang.RuntimeException

class ClienteNaoInformadoException: RuntimeException {

    constructor(msg: String, cause: Throwable): super(msg, cause)
    constructor(msg: String): super(msg)
}