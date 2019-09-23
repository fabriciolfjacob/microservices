package br.com.curso.pedido.domain.exceptions

import java.lang.RuntimeException

class PedidoNaoLocalizadoException: RuntimeException {
    constructor(msg: String, cause: Throwable): super(msg, cause)
    constructor(msg: String): super(msg)
}