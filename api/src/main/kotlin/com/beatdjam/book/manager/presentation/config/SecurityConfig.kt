package com.beatdjam.book.manager.presentation.config

import com.beatdjam.book.manager.application.service.AuthenticationService
import com.beatdjam.book.manager.application.service.security.BookManagerUserDetailsService
import com.beatdjam.book.manager.domain.enum.RoleType
import com.beatdjam.book.manager.presentation.handler.BookManagerAccessDeniedHandler
import com.beatdjam.book.manager.presentation.handler.BookManagerAuthenticationEntryPoint
import com.beatdjam.book.manager.presentation.handler.BookManagerAuthenticationFailureHandler
import com.beatdjam.book.manager.presentation.handler.BookManagerAuthenticationSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(private val authenticationService: AuthenticationService) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests()
            .requestMatchers("/login").permitAll()
            .requestMatchers("/admin/**").hasAuthority(RoleType.ADMIN.toString())
            .anyRequest().authenticated()
            .and()
            .csrf().disable()
            .formLogin()
            .loginProcessingUrl("/login")
            .usernameParameter("email")
            .passwordParameter("pass")
            .successHandler(BookManagerAuthenticationSuccessHandler())
            .failureHandler(BookManagerAuthenticationFailureHandler())
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(BookManagerAuthenticationEntryPoint())
            .accessDeniedHandler(BookManagerAccessDeniedHandler())
            .and()
            .cors()
            .configurationSource(corsConfigurationSource())

        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService = BookManagerUserDetailsService(authenticationService)

    @Bean
    fun encoder() = BCryptPasswordEncoder()

    private fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration().also {
            it.addAllowedMethod(CorsConfiguration.ALL)
            it.addAllowedHeader(CorsConfiguration.ALL)
            it.addAllowedOrigin("http://localhost:8081")
            it.allowCredentials = true
        }

        return UrlBasedCorsConfigurationSource().also {
            it.registerCorsConfiguration("/**", corsConfiguration)
        }
    }
}