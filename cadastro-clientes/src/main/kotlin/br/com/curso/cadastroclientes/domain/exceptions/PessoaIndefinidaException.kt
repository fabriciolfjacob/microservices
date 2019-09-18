package br.com.curso.cadastroclientes.domain.exceptions

import java.lang.RuntimeException

class PessoaIndefinidaException: RuntimeException {

    constructor(msg: String, causa: Throwable): super(msg, causa)
    constructor(msg: String) : super(msg)
}