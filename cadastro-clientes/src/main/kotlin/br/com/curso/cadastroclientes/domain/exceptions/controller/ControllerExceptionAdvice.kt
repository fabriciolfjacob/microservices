package br.com.curso.cadastroclientes.domain.exceptions.controller

import br.com.curso.cadastroclientes.domain.exceptions.ObjetoDuplicadoException
import br.com.curso.cadastroclientes.domain.exceptions.ObjetoNaoEncontradoException
import br.com.curso.cadastroclientes.domain.exceptions.PessoaIndefinidaException
import br.com.curso.cadastroclientes.domain.exceptions.StandardError
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ControllerExceptionAdvice {

    private val logger = LoggerFactory.getLogger(ControllerAdvice::class.java)

    @ExceptionHandler(ObjetoNaoEncontradoException::class)
    fun objectNotFoundException(e: ObjetoNaoEncontradoException, request: HttpServletRequest): ResponseEntity<StandardError> {
        logger.error("Falha objeto não informado.")
        val err = StandardError(HttpStatus.NOT_FOUND.value() as Integer, e.message, System.currentTimeMillis())
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err)
    }

    @ExceptionHandler(ObjetoDuplicadoException::class)
    fun objectDuplicado(e: ObjetoDuplicadoException, request: HttpServletRequest): ResponseEntity<StandardError> {
        logger.error("Falha objeto duplicado.")
        val err = StandardError(HttpStatus.CONFLICT as Integer, e.message, System.currentTimeMillis())
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err)
    }

    @ExceptionHandler(PessoaIndefinidaException::class)
    fun pessoaIndefinida(e: ObjetoNaoEncontradoException, request: HttpServletRequest): ResponseEntity<StandardError> {
        logger.error("Falha objeto não encontrado..")
        val err = StandardError(HttpStatus.BAD_REQUEST as Integer, e.message, System.currentTimeMillis())
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err)
    }
}