package com.ruslan.pastebin.Pastebin.config;

import com.ruslan.pastebin.Pastebin.service.AuthService;
import com.ruslan.pastebin.Pastebin.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetails;

    public SecurityConfig(UserDetailsServiceImpl userDetails) {
        this.userDetails = userDetails;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register").permitAll()
                        .requestMatchers("/posts/delete/**", "/comments/delete/**","/users/delete/**").hasRole("ADMIN")
                        .requestMatchers("/posts/**", "/comments/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetails)
                .httpBasic(httpBasic -> httpBasic
                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/auth/register"))
                );
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}