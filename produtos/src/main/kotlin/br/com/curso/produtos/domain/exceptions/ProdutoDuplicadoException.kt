package br.com.curso.produtos.domain.exceptions

import java.lang.RuntimeException

class ProdutoDuplicadoException: RuntimeException {

    constructor(msg: String, cause: Throwable): super(msg, cause)
    constructor(msg: String): super(msg)
}