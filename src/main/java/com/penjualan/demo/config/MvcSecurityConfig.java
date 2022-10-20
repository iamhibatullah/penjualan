package com.penjualan.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import javax.persistence.Basic;

@Configuration
@EnableWebSecurity
public class MvcSecurityConfig{


    @Bean
    @Order(2)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**","/user/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/user/loginForm")
                .loginProcessingUrl("/authenticating")
                .and().logout()
                .and().exceptionHandling().accessDeniedPage("/user/accessDenied");

        return http.build();
    }
}
