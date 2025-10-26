package io.devboxd.boxd_api.infrastructure.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityCustomConfiguration {
    private final UserAuthenticationFilter userAuthenticationFilter;

    static final List<String> URL_WHITELIST = List.of("/auth", "/auth/**", "/swagger/**", "/h2-console");
    static final List<String> URL_ADMIN = List.of("/users/**");

    public SecurityCustomConfiguration(UserAuthenticationFilter userAuthenticationFilter) {
        this.userAuthenticationFilter = userAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("*").authenticated()
                        .requestMatchers(SecurityCustomConfiguration.URL_WHITELIST.toArray(new String[0])).permitAll()
                        .requestMatchers(SecurityCustomConfiguration.URL_ADMIN.toArray(new String[0])).hasRole("ADMIN")
                ).addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
