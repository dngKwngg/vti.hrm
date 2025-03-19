package com.example.vti.config;

import com.example.vti.exceptions.ErrorResponse;
import com.example.vti.handlers.CustomAccessDeniedHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.vti.security.filters.JwtFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity 
public class SecurityConfig {
	@Value("${api.version}")  // Inject the value from application.properties
    private String apiVersion;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;

	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable()) // Disable CSRF
				// Configure application to be stateless, don't create or use HTTP session to track state
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				// Define authorization rules
				.authorizeHttpRequests(auth -> auth
						// All requests match pattern are allowed without authentication
						.requestMatchers("/" + apiVersion + "/users/login").permitAll()
						.requestMatchers("/" + apiVersion + "/tests").permitAll()
						.requestMatchers("/" + apiVersion + "/log").permitAll()
						// All other requests require authentication
						.anyRequest().authenticated()
				)
				// Handle authentication and authorization errors
				.exceptionHandling(exception -> exception
						.accessDeniedHandler(customAccessDeniedHandler)
				)
				// Add custom JWT token filter before Spring's default username/password authentication filter
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// Expose Spring Security's AuthenticationManager as a bean
	// AuthenticationManager is core interface that process authentication requests
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
}
