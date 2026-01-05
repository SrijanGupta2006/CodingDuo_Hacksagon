package com.CodingDupo.projectAPI.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.CodingDupo.projectAPI.Filter.JWTFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private JWTFilter jwtfilter;
    @Autowired
    public SecurityConfiguration(JWTFilter jwtfilter) {
        this.jwtfilter = jwtfilter;
    }
    @Bean
    public SecurityFilterChain securityContextHolder(HttpSecurity http){
        return http
                    .csrf(c->c.disable())
                    .sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(c->c
                                                .requestMatchers("/user/register","/user/login","/user/refresh")
                                                .permitAll()
                                                .anyRequest()
                                                .authenticated()
                                            )
                    .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class)
                    .formLogin(c->c.disable())
                    .build();
    }
}
