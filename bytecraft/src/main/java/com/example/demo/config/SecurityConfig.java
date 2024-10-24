package com.example.demo.config; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/register", "/auth/login").permitAll()
                
                .requestMatchers(HttpMethod.GET, "/api/people/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/people/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/people/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/people/**").hasRole("ADMIN")
                
               
                .requestMatchers(HttpMethod.GET, "/api/projects/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/projects/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/projects/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/projects/**").hasRole("ADMIN")
    
                .requestMatchers(HttpMethod.GET, "/api/skills/**").hasAnyRole("USER", "ADMIN")
    
               
                .requestMatchers(HttpMethod.POST, "/api/skills/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/skills/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/skills/**").hasRole("ADMIN")
                
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/auth/login")
                .defaultSuccessUrl("/auth/login/succesfull", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/auth/login?logout")
                .permitAll()
            );
        return http.build();
    }
    



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
