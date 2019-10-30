package br.com.curso.recomendacaoproduto.domain.services

import br.com.curso.recomendacaoproduto.domain.Recomendacao
import br.com.curso.recomendacaoproduto.domain.dto.RecomendacaoDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface RecomendacaoService {

    fun salvar(dto: RecomendacaoDto): Mono<Recomendacao>
    fun findAll(): Flux<Recomendacao>
    fun delete(id: String)
    fun update(dto: RecomendacaoDto, id: String)
    fun findById(id: String): Mono<Recomendacao>
    fun findByCodeProduto(codeProduto: String): Flux<Recomendacao>
}