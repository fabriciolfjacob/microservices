package br.com.curso.produtos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableHystrix
@EnableCircuitBreaker
class ProdutosApplication

fun main(args: Array<String>) {
	runApplication<ProdutosApplication>(*args)
}
