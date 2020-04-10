package com.security.JDBCauthentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .withUser(
                        User.withUsername("commonuser")
                        .password(bCryptPasswordEncoder().encode("password1"))
                        .roles("user")
                )
                .withUser(
                        User.withUsername("privilegedUser")
                                .password(bCryptPasswordEncoder().encode("password2"))
                                .roles("privilege")
                )
                .withUser(
                        User.withUsername("admin")
                                .password(bCryptPasswordEncoder().encode("admin123"))
                                .roles("admin")
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/specialOffer/**").hasAnyRole("admin","privilege")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .defaultSuccessUrl("/home", true)
                .and()
                .httpBasic();
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
