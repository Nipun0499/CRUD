package com.institution.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    /**
     * @param http object to configure.
     * @return an object that contains the configured security filters.
     */
    @Bean
    public SecurityFilterChain filterChain(
            final HttpSecurity http)
            throws Exception {
        http.cors().configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(
                            List.of(
                                    "http://localhost:8989"
                            )
                    );
                    configuration.setAllowedMethods(
                            Arrays.asList(
                                    "GET",
                                    "PUT",
                                    "POST",
                                    "PATCH",
                                    "DELETE",
                                    "OPTIONS"
                            )
                    );
                    configuration.setAllowCredentials(Boolean.TRUE);
                    configuration.setAllowedHeaders(Arrays.asList(
                                    HttpHeaders.ORIGIN,
                                    HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD,
                                    HttpHeaders.CACHE_CONTROL,
                                    HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
                                    HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,
                                    HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,
                                    HttpHeaders.AUTHORIZATION,
                                    HttpHeaders.CONTENT_TYPE,
                                    HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS,
                                    "X-REMOTE-USER-EMAIL",
                                    HttpHeaders.ACCESS_CONTROL_MAX_AGE,
                                    HttpHeaders.CACHE_CONTROL
                            )
                    );
                    configuration.addExposedHeader(
                            HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS
                    );
                    return configuration;
                })
                .and().csrf().disable()
                .authorizeRequests()
                .requestMatchers("/**").permitAll()
                .requestMatchers("/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/actuator/*").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .anyRequest().authenticated();

        return http.build();
    }
}
