package com.anjaniy.funfactsbackend.config;

import com.anjaniy.funfactsbackend.security.CustomAccessDeniedHandler;
import com.anjaniy.funfactsbackend.security.JwtAuthenticationEntryPoint;
import com.anjaniy.funfactsbackend.security.JwtAuthenticationFilter;
import com.anjaniy.funfactsbackend.services.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final UserDetailServiceImpl userDetailsService;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private static final List<String> CROSS_ORIGINS = List.of(
        "http://localhost:3000"
    );
    private static final List<String> CROSS_ORIGIN_METHODS = List.of(
        "*"
    );
    private static final List<String> CROSS_ORIGIN_HEADERS = List.of(
        "*"
    );

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, UserDetailServiceImpl userDetailsService, CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.userDetailsService = userDetailsService;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .cors((cors) -> {
                CorsConfigurationSource configurationSource = (request) -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(CROSS_ORIGINS);
                    corsConfiguration.setAllowedMethods(CROSS_ORIGIN_METHODS);
                    corsConfiguration.setAllowedHeaders(CROSS_ORIGIN_HEADERS);
                    return corsConfiguration;
                };
                cors.configurationSource(configurationSource);
            })
            .exceptionHandling((exception) -> {
                exception.authenticationEntryPoint(jwtAuthenticationEntryPoint);
                exception.accessDeniedHandler(customAccessDeniedHandler);
            })
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests((auth) ->
                auth
                    .requestMatchers("/api/v1/auth/**").permitAll()
                    .anyRequest().authenticated()
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
