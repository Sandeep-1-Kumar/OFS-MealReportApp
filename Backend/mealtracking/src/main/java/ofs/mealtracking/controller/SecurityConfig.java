package ofs.mealtracking.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
// kosalya is editing this
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> {
            try {
                cors.disable()
                        .csrf(csrf -> csrf.disable());
            } catch (Exception e) {
                e.printStackTrace();
            }
        })
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/OFS/admin").permitAll()
                                .antMatchers("/OFS/siteuser").permitAll()
                );
        return http.build();
    }
}
