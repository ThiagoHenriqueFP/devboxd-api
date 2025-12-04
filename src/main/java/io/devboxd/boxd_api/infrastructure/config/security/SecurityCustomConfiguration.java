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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityCustomConfiguration {
    private final UserAuthenticationFilter userAuthenticationFilter;

    static final String[] URL_WHITELIST = {
            "/auth",
            "/auth/**",
            "/h2-console",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**"
    };
    static final List<String> URL_ADMIN = List.of("/users/**");

    public SecurityCustomConfiguration(UserAuthenticationFilter userAuthenticationFilter) {
        this.userAuthenticationFilter = userAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(URL_WHITELIST).permitAll()
                        .requestMatchers(SecurityCustomConfiguration.URL_ADMIN.toArray(new String[0])).hasRole("ADMIN")
                        .anyRequest().authenticated()
                ).addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(http -> {
                    CorsConfiguration conf = new CorsConfiguration();
                    conf.setAllowedHeaders(List.of("*"));
                    conf.setAllowedOrigins(List.of("*"));
                    conf.setAllowedMethods(List.of("*"));
                    return conf;
                }))
                .build();
    }
}
