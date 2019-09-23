package br.com.curso.produtos.domain.repositories

import br.com.curso.produtos.domain.model.Produto
import org.springframework.data.mongodb.repository.MongoRepository

interface ProdutoRepository: MongoRepository<Produto, String>{

    fun findByDescricao(descricao: String): Produto
}