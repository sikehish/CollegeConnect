package com.sikehish.collegeconnect.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

//    @Autowired
//    private WebUserService webUserService; // Not used directly, but it's good to keep for future enhancements

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login", "/api/auth/test").permitAll()  // Permit login and test endpoints
                        .requestMatchers("/api/student/**").hasAuthority("STUDENT")  // Allow only students to access this endpoint
                        .requestMatchers("/api/faculty/**").hasAuthority("FACULTY_MEMBER")  // Allow only faculty members
                        .requestMatchers("/api/admin/**").hasAuthority("ADMINISTRATOR")  // Allow only administrators
//                        .requestMatchers("/api/common/**").hasAnyRole("STUDENT", "FACULTY_MEMBER", "ADMINISTRATOR")  // Allow access to all roles
                        .anyRequest().authenticated()  // All other requests must be authenticated
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Use stateless sessions
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
