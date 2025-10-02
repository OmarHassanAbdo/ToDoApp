package com.example.todo.security;

import com.example.todo.security.JWT.JwtAuthFilter;
import com.example.todo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Bean
    public JwtAuthFilter authenticationJwtTokenFilter() {
        return new JwtAuthFilter();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**").permitAll()     // public: register, login
                        .requestMatchers("/api/task/**").authenticated() // must be logged in
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // only admin
                        .anyRequest().authenticated() // everything else needs login
                );
                //.formLogin(form -> form.permitAll()) // default login form (for testing)
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    /*
What is it?
The AuthenticationManager is like the boss.
It doesn’t do the checking itself.
Instead, it delegates the job to the AuthenticationProvider (the worker).
Why use AuthenticationConfiguration?
Spring already knows about your providers (like the one above).
The AuthenticationConfiguration just says:
👉 “Build me an AuthenticationManager using all the providers that are registered.”
➡️ So this method says:
👉 “Spring, please create and expose a global AuthenticationManager bean that uses my provider(s).”
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
