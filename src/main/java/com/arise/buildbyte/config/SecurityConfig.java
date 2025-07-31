package com.arise.buildbyte.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //.csrf().disable() // Disable CSRF for APIs
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/**",
                                "/api/**",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/webjars/**"
                        ).permitAll() // Allow public access to auth APIs
                        .anyRequest().authenticated()
                )
                //.httpBasic();
                .httpBasic(Customizer.withDefaults()); // Uses default HTTP Basic configuration
                //.httpBasic(withDefaults()); // or formLogin() if using form-based login

        return http.build();
    }


}
