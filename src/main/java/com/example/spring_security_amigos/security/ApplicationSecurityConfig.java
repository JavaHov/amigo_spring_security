package com.example.spring_security_amigos.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.example.spring_security_amigos.security.ApplicationUserRole.*;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig {



    @Bean
    public UserDetailsManager users() {

        UserDetails olauser =
                User.builder()
                        .username("olalindgard")
                        .password(passwordEncoder().encode("rasmus"))
          //              .roles(STUDENT.name()).build(); // ROLE_STUDENT
                        .authorities(STUDENT.getGrantedAuthorities())
                        .build();

        UserDetails ronnieadmin =
                User.builder()
                        .username("ronnie")
                        .password(passwordEncoder().encode("vilma"))
          //              .roles(ADMIN.name()).build(); // ROLE_ADMIN
                        .authorities(ADMIN.getGrantedAuthorities())
                        .build();


        UserDetails evamariatrainee =
                User.builder()
                        .username("evamaria")
                        .password(passwordEncoder().encode("vidar"))
                     //   .roles(ADMIN_TRAINEE.name()).build(); // ROLE_ADMIN_TRAINEE
                        .authorities(ADMIN_TRAINEE.getGrantedAuthorities())
                        .build();

        return new InMemoryUserDetailsManager(olauser, ronnieadmin, evamariatrainee);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("index", "/css/*", "/js/*").permitAll()
                        .requestMatchers("/api/**").hasRole(STUDENT.name())
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());

        return http.build();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
