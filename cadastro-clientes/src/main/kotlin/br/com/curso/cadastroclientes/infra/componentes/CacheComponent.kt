package br.com.curso.cadastroclientes.infra.componentes

import org.springframework.cache.CacheManager
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
class CacheComponent {

    @Bean
    fun cacheManager(): CacheManager{
        return ConcurrentMapCacheManager()
    }
}