package com.publisher.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity//(debug = true)
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter
{

    //@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/console/**",
                "/v2/api-docs",
                "/v2/api-docs/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/csrf",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/css/**","/js/**","/images/**",
                "/console/**/*.jsp",
                "/console/**/*.do"

        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login","/logout").permitAll()
                       .anyRequest().authenticated()
                       .and()
                .formLogin().and()
                .csrf()
                        .disable();
    }

}


