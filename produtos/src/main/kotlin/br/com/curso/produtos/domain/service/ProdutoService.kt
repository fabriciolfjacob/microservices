package br.com.curso.produtos.domain.service

import br.com.curso.produtos.domain.dto.ProdutoDto
import br.com.curso.produtos.domain.model.Produto

interface ProdutoService {

    fun salvar(dto: ProdutoDto): Produto
    fun update(dto: ProdutoDto, id: String)
    fun delete(id: String)
    fun findById(id: String): Produto
    fun findAll(): List<Produto>
}