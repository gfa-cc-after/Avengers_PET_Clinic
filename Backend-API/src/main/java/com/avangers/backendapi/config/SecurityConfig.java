package com.avangers.backendapi.config;

import com.avangers.backendapi.filter.AuthJwtTokenFilter;
import com.avangers.backendapi.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private UserServiceImpl userService;
    private AuthenticationEntryPoint authenticationEntryPoint;
    private AuthJwtTokenFilter authJwtTokenFilter;

    public SecurityConfig(UserServiceImpl userService, AuthenticationEntryPoint authenticationEntryPoint, AuthJwtTokenFilter authJwtTokenFilter) {
        this.userService = userService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authJwtTokenFilter = authJwtTokenFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(16);
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    @Primary // We need to define it as a Primary Bean so that the default authentication manager instance
    // does not get picked up. Instead, our authentication manager builder
    // with our custom user details service and password encoder will be picked up by the Spring context.
    public AuthenticationManagerBuilder configureAuthenticationManagerBuilder(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder;
    }

    // ?????????? I don't know how to manage endpoints in this method for now ?????????
    //.................................................................................
    //.................................................................................
    //.................................................................................
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())  // Enables CORS with default settings
                .csrf(csrf -> csrf.disable())  // Disables CSRF protection
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(authenticationEntryPoint))  // Sets custom authentication entry point
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Configures stateless session management
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/register").permitAll()  // Allows unrestricted access to /register
                                .requestMatchers("/health").permitAll()  // Allows unrestricted access to /health
                                .anyRequest().authenticated()  // Requires authentication for all other requests
                )
                .addFilterBefore(authJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);  // Adds custom JWT filter before UsernamePasswordAuthenticationFilter

        return http.build();
    }
}