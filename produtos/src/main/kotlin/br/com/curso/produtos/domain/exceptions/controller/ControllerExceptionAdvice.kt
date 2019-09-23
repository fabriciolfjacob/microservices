package br.com.curso.produtos.domain.exceptions.controller

import br.com.curso.produtos.domain.exceptions.ObjectNotFoundException
import br.com.curso.produtos.domain.exceptions.ProdutoDuplicadoException
import br.com.curso.produtos.domain.exceptions.ProdutoPrecoZeroException
import br.com.curso.produtos.domain.exceptions.StandardError
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ControllerExceptionAdvice {

    private val logger = LoggerFactory.getLogger(ControllerAdvice::class.java)

    @ExceptionHandler(ProdutoPrecoZeroException::class)
    fun produtoPrecoZero(e: ProdutoPrecoZeroException, request: HttpServletRequest): ResponseEntity<StandardError>{
        logger.error("Produto com preço zerado.")
        var err = StandardError(HttpStatus.NOT_ACCEPTABLE.value() as Integer, e.message, System.currentTimeMillis())
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err)
    }

    @ExceptionHandler(ProdutoDuplicadoException::class)
    fun produtoDuplicado(e: ProdutoDuplicadoException, request: HttpServletRequest): ResponseEntity<StandardError>{
        logger.error("Produto já existente")
        var err = StandardError(HttpStatus.NOT_ACCEPTABLE.value() as Integer, e.message, System.currentTimeMillis())
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err)
    }

    @ExceptionHandler(ObjectNotFoundException::class)
    fun objectNotFoundException(e: ObjectNotFoundException, request: HttpServletRequest): ResponseEntity<StandardError>{
        logger.error("Produto não encontrado.")
        var err = StandardError(HttpStatus.NOT_FOUND.value() as Integer, e.message, System.currentTimeMillis())
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err)
    }
}