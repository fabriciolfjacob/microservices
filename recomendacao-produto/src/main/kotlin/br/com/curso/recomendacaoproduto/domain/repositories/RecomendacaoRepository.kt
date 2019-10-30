package br.com.curso.recomendacaoproduto.domain.repositories

import br.com.curso.recomendacaoproduto.domain.Recomendacao
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface RecomendacaoRepository: ReactiveMongoRepository<Recomendacao, String> {

    fun findByCodeProduto(code: String): Flux<Recomendacao>
}