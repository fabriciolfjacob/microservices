package br.com.curso.servergateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient



@SpringBootApplication
@EnableDiscoveryClient
class ServerGatewayApplication

fun main(args: Array<String>) {
	runApplication<ServerGatewayApplication>(*args)
}

@Bean
@LoadBalanced
fun loadBalancedWebClientBuilder(): WebClient.Builder {
	return WebClient.builder()
}
