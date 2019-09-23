package br.com.curso.pedido

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients("br.com.curso.pedido")
@EnableDiscoveryClient
class PedidoApplication

fun main(args: Array<String>) {
	runApplication<PedidoApplication>(*args)
}
