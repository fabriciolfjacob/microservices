package br.com.curso.recomendacaoproduto

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class RecomendacaoProdutoApplication

fun main(args: Array<String>) {
	runApplication<RecomendacaoProdutoApplication>(*args)
}
