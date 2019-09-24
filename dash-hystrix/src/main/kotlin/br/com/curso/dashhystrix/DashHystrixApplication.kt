package br.com.curso.dashhystrix

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard
import org.springframework.cloud.netflix.turbine.EnableTurbine

@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine
@EnableDiscoveryClient
class DashHystrixApplication
///actuator/hystrix.stream -- de cada serviço, resolver quando esta no docker não funciona os stream
//http://localhost:9999/hystrix
fun main(args: Array<String>) {
	runApplication<DashHystrixApplication>(*args)
}
