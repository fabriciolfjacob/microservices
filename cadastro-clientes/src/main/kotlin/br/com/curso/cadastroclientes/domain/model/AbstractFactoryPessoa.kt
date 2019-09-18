package br.com.curso.cadastroclientes.domain.model

abstract class AbstractFactoryPessoa {
    abstract fun getPessoa(nome: String, endereco: String, documento: String): Entidade
}