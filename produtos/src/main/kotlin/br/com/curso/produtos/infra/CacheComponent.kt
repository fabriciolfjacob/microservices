package br.com.curso.produtos.infra

import org.apache.tomcat.util.collections.ConcurrentCache
import org.springframework.cache.CacheManager
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Configuration
class CacheComponent {

    @Bean
    fun cacheManager(): CacheManager{
        return ConcurrentMapCacheManager("produtos")
    }
}