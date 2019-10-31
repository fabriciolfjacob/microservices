package br.com.curso.recomendacaoproduto.domain.services

import br.com.curso.recomendacaoproduto.domain.Recomendacao
import br.com.curso.recomendacaoproduto.domain.dto.RecomendacaoDto
import br.com.curso.recomendacaoproduto.domain.repositories.RecomendacaoRepository
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.lang.RuntimeException

@Service
class RecomendacaoServiceImpl : RecomendacaoService {

    @Autowired
    lateinit var recomendacaoRepository: RecomendacaoRepository

    override fun salvar(dto: RecomendacaoDto): Mono<Recomendacao> {
        var obj = convertDtoToObject(dto)
        return recomendacaoRepository.save(obj)
    }

    private fun convertDtoToObject(dto: RecomendacaoDto): Recomendacao{
        return Recomendacao(null,dto.codeProduto, dto.descricao)
    }

    @Retry(name ="recomendacao")
    @CircuitBreaker(name = "recomendacao")
    override fun findAll(): Flux<Recomendacao> {
        return recomendacaoRepository.findAll().onErrorMap {
            e -> throw RuntimeException("Falha ao carregar os dados de recomendação. Detalhes: ${e.message}")
        }
    }

    override fun delete(id: String) {
        recomendacaoRepository.deleteById(id)
    }

    override fun update(dto: RecomendacaoDto, id: String) {
        recomendacaoRepository.findById(id)
                .map { atualizarDtoToObj(dto, it) }
    }

    private fun atualizarDtoToObj(dto: RecomendacaoDto, obj: Recomendacao): Recomendacao{
        obj.codeProduto = dto.codeProduto
        obj.descricao = dto.descricao
        return obj
    }

    override fun findById(id: String): Mono<Recomendacao> {
        return recomendacaoRepository.findById(id)
    }

    override fun findByCodeProduto(codeProduto: String): Flux<Recomendacao> {
        return recomendacaoRepository.findByCodeProduto(codeProduto)
    }
}