package br.com.curso.produtos.domain.exceptions

import java.io.Serializable

data class StandardError(
        val status: Integer,
        val msg: String?,
        val timeStamp: Long
): Serializable{
    companion object {
        private const val serialVersionUID = 1L
    }
}