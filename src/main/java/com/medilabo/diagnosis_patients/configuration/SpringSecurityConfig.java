package com.medilabo.diagnosis_patients.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationWebFilter;

    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        /**
                         * Regles d'authentification : accès à l'appli
                         */
                        .anyRequest().authenticated()
                )
                //UsernamePasswordAuthenticationFilter est le filtre par defaut de spring security.
                .addFilterBefore(jwtAuthenticationWebFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}

