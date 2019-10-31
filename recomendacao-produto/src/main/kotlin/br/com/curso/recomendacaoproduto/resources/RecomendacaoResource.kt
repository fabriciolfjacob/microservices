package br.com.curso.recomendacaoproduto.resources

import br.com.curso.recomendacaoproduto.domain.Recomendacao
import br.com.curso.recomendacaoproduto.domain.dto.RecomendacaoDto
import br.com.curso.recomendacaoproduto.domain.services.RecomendacaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono
import javax.validation.Valid
import reactor.core.publisher.Flux
import reactor.core.publisher.onErrorReturn
import java.lang.RuntimeException


@RestController
@RequestMapping("/recomendacao")
@CrossOrigin(origins = arrayOf("*"))
class RecomendacaoResource {

    @Autowired
    lateinit var recomendacaoService: RecomendacaoService

    @PostMapping
    fun create(@Valid @RequestBody dto: RecomendacaoDto, uriBuilder: UriComponentsBuilder): Mono<ResponseEntity<Unit>> {
        return recomendacaoService.salvar(dto)
                .map{
                    ResponseEntity.created(uriBuilder.path("/{id}").buildAndExpand(it.id).toUri()).build<Unit>()
                }
    }

    @GetMapping(produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun findAll(): Flux<Recomendacao> {
        return recomendacaoService.findAll()
                .switchIfEmpty { ResponseEntity.notFound().build<Unit>() }
                .onErrorReturn(findAllFallback()).log()
    }

    fun findAllFallback(): Recomendacao{
        return Recomendacao("", "9999", "Fallbackx")
    }

    @GetMapping("/{id}",produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun findById(@PathVariable("id") id: String): Mono<Recomendacao> {
        return recomendacaoService.findById(id)
    }
}