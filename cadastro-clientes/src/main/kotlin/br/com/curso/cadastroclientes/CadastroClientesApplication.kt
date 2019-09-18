package br.com.curso.cadastroclientes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableHystrix
@EnableCircuitBreaker
class CadastroClientesApplication

fun main(args: Array<String>) {
	runApplication<CadastroClientesApplication>(*args)
}
