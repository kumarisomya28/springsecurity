package com.spring_security.springSecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {

    private static final String[] WHITE_LIST_URLS = {
            "/hello",
            "/register",
            "/verifyRegistration*",
            "/resendVerifyToken*"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//    http
//            .cors()
//            .and()
//            .csrf()
//            .disable()
//            .authorizeHttpRequests()
//            .
//
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> {
                try {
                    authorize
                            .requestMatchers(WHITE_LIST_URLS).permitAll()
                            .anyRequest().authenticated()
                            .and()
                            .oauth2Login(oauth2login ->
                                    oauth2login.loginPage("/oauth2/authorization/api-client-oidc"))
                            .oauth2Client(Customizer.withDefaults());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

    return http.build();
}
}
