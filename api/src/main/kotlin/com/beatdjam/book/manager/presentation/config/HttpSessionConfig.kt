package com.beatdjam.book.manager.presentation.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession

@Configuration
@EnableRedisHttpSession
class HttpSessionConfig {
    @Bean
    fun connectionFactory(): JedisConnectionFactory {
        // TODO application.ymlとかに定義したい
        val redisStandaloneConfiguration = RedisStandaloneConfiguration().also {
            it.hostName = "redis"
            it.port = 6379
        }
        return JedisConnectionFactory(redisStandaloneConfiguration)
    }
}