package com.jetbrains.employees_all.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        // РОУТЫ АВТОРИЗАЦИИ: ПУБЛИЧНЫЙ, АДМИНИСТРАТОР, ПОЛЬЗОВАТЕЛЬ
                        .requestMatchers("/api/public").permitAll()
                        .requestMatchers("/api/admin").hasAnyRole("ADMIN")
                        .requestMatchers("/api/user").hasAnyRole("USER")

                        // Роуты которые были созданы для Админа
                        .requestMatchers("/api/firsttable").permitAll()
                        .requestMatchers("/api/firsttable/**").permitAll()
                        .requestMatchers("/api/managers").permitAll()
                        .requestMatchers("/api/managers/**").permitAll()
                        .requestMatchers("/api/status_employee").permitAll()
                        .requestMatchers("/api/status_employee/**").permitAll()

                        //Роуты которые были созданы для Пользователя
                        .requestMatchers("/api/employees").permitAll()
                        .requestMatchers("/api/employees/**").permitAll()
                        .requestMatchers("/api/roles").permitAll()
                        .requestMatchers("/api/roles/**").permitAll()
                        .requestMatchers("/api/status").permitAll()
                        .requestMatchers("/api/status/**").permitAll()

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // БАЗОВАЯ АВТОРИЗАЦИЯ

        return http.build();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        // Использует стандартные таблицы users/authorities
        return new JdbcUserDetailsManager(dataSource);
    }

    // Пароли сравниваются как есть (в открытом виде)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
