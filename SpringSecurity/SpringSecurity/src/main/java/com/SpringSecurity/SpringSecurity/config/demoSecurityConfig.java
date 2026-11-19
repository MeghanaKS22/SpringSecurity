package com.SpringSecurity.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class demoSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.csrf(csrf->csrf.disable())//filtercahin
                .authorizeHttpRequests(auth->auth//authorization
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/home").authenticated().anyRequest().permitAll())

                .formLogin(login->login  //aunthentication
                        .loginPage("/login")
                        .defaultSuccessUrl("/home",true).permitAll());
        return http.build();
    }
}

//client-filterChain-authentication-authorization-controller
//localhost:8080-default url for that we dont get the login page because it is aunthenticated
//for spring security in login.html we no need to change the name we should only use same name like for ex:username
//we should use name="username"
//if dont use formlogin() then default ui page will be displayed,to display customLoginpage we use(FormLogin method)
//permitAll()-without aunthentication it will give access to login
//@EnableWebSecurity-for security thing