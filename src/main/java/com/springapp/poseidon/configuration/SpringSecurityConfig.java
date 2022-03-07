package com.springapp.poseidon.configuration;

import com.springapp.poseidon.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**", "/trade/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/user/**").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().defaultSuccessUrl("/bidList/list", true)
                .and().oauth2Login().defaultSuccessUrl("/bidList/list", true)
                .and().logout().logoutUrl("/app-logout").logoutSuccessUrl("/login")
                .and().exceptionHandling().accessDeniedPage("/error");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

