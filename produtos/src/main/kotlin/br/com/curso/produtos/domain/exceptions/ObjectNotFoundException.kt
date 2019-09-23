package br.com.curso.produtos.domain.exceptions


class ObjectNotFoundException: RuntimeException{

    constructor(msg: String, cause: Throwable): super(msg, cause)
    constructor(msg: String): super(msg)
}