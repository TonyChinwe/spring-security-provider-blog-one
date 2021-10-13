package com.phisoft.springsecurityproviderblogone.appconfig;

import com.phisoft.springsecurityproviderblogone.security.AppFilter;
import com.phisoft.springsecurityproviderblogone.security.CustomAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ApplicationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthProvider provider;

    @Autowired
    private AppFilter appFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(appFilter, BasicAuthenticationFilter.class);
        http.authorizeRequests().anyRequest().permitAll();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }



}
