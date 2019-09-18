package br.com.curso.cadastroclientes.domain.model

import br.com.caelum.stella.validation.CNPJValidator
import br.com.caelum.stella.validation.CPFValidator
import br.com.curso.cadastroclientes.domain.exceptions.PessoaIndefinidaException

class PessoaFactory: AbstractFactoryPessoa() {

    override fun getPessoa(nome: String, endereco: String, documento: String): Entidade {
        if(validarCpf(documento)){
            return PessoaFisica(nome, endereco, documento)
        }

        if(validarCnpj(documento)){
            return PessoaJuridica(nome, endereco, documento)
        }

        throw PessoaIndefinidaException("Documento inválido. Valor informado: $documento")
    }

    private fun validarCpf(documento: String): Boolean{
        var validador = CPFValidator().invalidMessagesFor(documento)
        return validador.size == 0
    }

    private fun validarCnpj(documento: String): Boolean{
        var validador = CNPJValidator().invalidMessagesFor(documento)
        return validador.size == 0
    }
}