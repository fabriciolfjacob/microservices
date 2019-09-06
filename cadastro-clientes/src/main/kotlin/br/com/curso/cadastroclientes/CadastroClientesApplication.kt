package br.com.curso.cadastroclientes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class CadastroClientesApplication

fun main(args: Array<String>) {
	runApplication<CadastroClientesApplication>(*args)
}
