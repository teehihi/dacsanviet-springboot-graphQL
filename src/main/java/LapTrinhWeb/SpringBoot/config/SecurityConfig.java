package LapTrinhWeb.SpringBoot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/graphql", "/graphiql/**", "/", "/products", "/users", "/categories", "/upload/**", "/images/**").permitAll()
                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions().sameOrigin());
        
        return http.build();
    }
}