package com.github.projetoifsc.estagios.app.configs;

import com.github.projetoifsc.estagios.app.security.ExceptionHandlerFilter;
import com.github.projetoifsc.estagios.app.security.auth.CustomUserDetailService;
import com.github.projetoifsc.estagios.app.security.auth.DelegatedAuthenticationEntryPoint;
import com.github.projetoifsc.estagios.app.security.auth.JwtAuthenticationFilter;
import com.github.projetoifsc.estagios.app.security.ratelimit.RateLimitFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig {


    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final RateLimitFilter rateLimitFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final CustomUserDetailService customUserDetailService;
    private final PasswordEncoder passwordEncoder;
    private final DelegatedAuthenticationEntryPoint delegatedAuthenticationEntryPoint;

    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, RateLimitFilter rateLimitFilter, ExceptionHandlerFilter exceptionHandlerFilter, CustomUserDetailService customUserDetailService, PasswordEncoder passwordEncoder, DelegatedAuthenticationEntryPoint delegatedAuthenticationEntryPoint) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.rateLimitFilter = rateLimitFilter;
        this.exceptionHandlerFilter = exceptionHandlerFilter;
        this.customUserDetailService = customUserDetailService;
        this.passwordEncoder = passwordEncoder;
        this.delegatedAuthenticationEntryPoint = delegatedAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
        return http
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(rateLimitFilter, LogoutFilter.class)
            .addFilterBefore(exceptionHandlerFilter, RateLimitFilter.class)
            .cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .securityMatcher("/**") // map current config to given resource path
            .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .formLogin(AbstractHttpConfigurer::disable)
            .exceptionHandling(h -> h.authenticationEntryPoint(delegatedAuthenticationEntryPoint))
            .authorizeHttpRequests(registry -> registry
                .requestMatchers(POST, "/api/v1/organizacoes").permitAll()
                .requestMatchers("/api/v1/organizacoes/**").authenticated()
                .requestMatchers("/api/v1/areas/**").authenticated()
                .requestMatchers("/api/v1/vagas/**").authenticated()
                .requestMatchers(GET, "/api/v1/auth/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
             )
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        var builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder
                .userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder);
        return builder.build();
    }

}
