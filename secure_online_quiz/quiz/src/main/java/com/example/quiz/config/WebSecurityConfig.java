package com.example.quiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the quiz application.
 * Configures endpoint access based on user roles and authentication status.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final CustomAuthenticationSuccessHandler successHandler;

    public WebSecurityConfig(CustomAuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    /**
     * Bean for BCryptPasswordEncoder to encode passwords.
     *
     * @return PasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures HTTP security for the application.
     * Sets up access control for different endpoints based on roles and
     * authentication.
     *
     * @param http HttpSecurity object to configure
     * @return SecurityFilterChain configured security filter chain
     * @throws Exception if there is an error during configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Authorize requests based on endpoint and user role
                .authorizeHttpRequests(authz -> authz
                        // Public endpoints - no authentication required
                        .requestMatchers("/quiz/login", "/quiz/register").permitAll()

                        // Admin endpoints - require ADMIN role
                        .requestMatchers("/quiz/quizlist").hasRole("ADMIN")

                        // User endpoints - require USER role
                        .requestMatchers("/quiz/quiz").hasRole("USER")

                        // API endpoints - require appropriate roles
                        .requestMatchers("/quiz/add-quiz", "/quiz/edit-quiz/**", "/quiz/quiz/**").hasRole("ADMIN")
                        .requestMatchers("/quiz/submit-answers").hasRole("USER")

                        // Question endpoint - allow both roles
                        .requestMatchers("/quiz/questions").authenticated()

                        // Result page - require authentication
                        .requestMatchers("/quiz/result").authenticated()

                        // All other requests require authentication
                        .anyRequest().authenticated())

                // Form login configuration
                .formLogin(formLogin -> formLogin
                        .loginPage("/quiz/login")
                        .successHandler(successHandler)
                        .permitAll())

                // Logout configuration
                .logout(logout -> logout
                        .logoutUrl("/quiz/logout")
                        .logoutSuccessUrl("/quiz/login")
                        .permitAll())

                // CSRF protection
                .csrf(csrf -> csrf.disable()); // Disable for simplicity; enable in production with proper CSRF token
                                               // handling

        return http.build();
    }
}
