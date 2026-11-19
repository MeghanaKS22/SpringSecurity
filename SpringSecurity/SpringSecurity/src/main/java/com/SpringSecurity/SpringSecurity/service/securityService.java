package com.SpringSecurity.SpringSecurity.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class securityService implements UserDetailsService {

//    Why implements UserDetailsService Spring Security needs a class that can:Load user details by username
//So it forces you to implement loadUserByUsername(String username)
//📌 You do NOT call this method yourself
//👉 Spring Security calls it automatically during login.

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        if(username.equals("skyllx")){
            return User
                    .withUsername("skyllx")
                    .password(passwordEncoder().encode("1234"))
                    .roles("USER")
                    .build();

        }
//        Purpose of this method When someone logs in:Spring Security takes the username Calls this method
//        Expects user details in return If user does not exist → throw exception.
//        Line-by-line meaning withUsername("skyllx") → sets username password(...) → encrypted password
//        roles("USER") → assigns role build() → creates UserDetails object🔐 Password is NOT stored as plain text
//        encode("1234") converts it into a hash Spring compares hashes, not raw passwords
        if(username.equals("admin")){
            return User.withUsername("admin").
                    password(passwordEncoder().encode("admin123"))
                    .roles("ADMIN").build();
        }
        throw new UsernameNotFoundException("user not found");

    }
//    same logic as previous user:Username → admin Password → admin123 Role → ADMIN Roles are later used to:
//    Allow or block URLs
//    STEP 7️⃣ User not foundthrow new UsernameNotFoundException("user not found");Why this is required
//    If username does not match any userAuthentication must failSpring Security catches this exception
//    Login page shows errorNever return null here.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
//What this does Creates a PasswordEncoder object Spring manages it as a beanUsed to encrypt passwords
//Important learning point:Bean should ideally be in a @Configuration class Putting it inside @Service works but is not best practice
//UserDetailsService → loads users
//UserDetails → represents user info
//PasswordEncoder → secures passwords
//roles() → controls authorization
