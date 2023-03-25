package com.example.Live_Stock_Updates.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/user/register/**").permitAll()
                                .requestMatchers("/user/login/**").permitAll()
                                .requestMatchers("stocks/**").permitAll()
                                .requestMatchers("/users").hasRole("ADMIN")
                );

        return http.build();
    }

    @Bean
    @Primary
    public AuthenticationManagerBuilder configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Scheduled(cron = "0 0 0 * * MON-FRI")
//    public void scheduleStockDataFetching() {
//        List<String> symbols = Arrays.asList("AAPL", "GOOG", "MSFT");
//        for (String symbol : symbols) {
//            saveStockData(symbol);
//        }
//    }



}
