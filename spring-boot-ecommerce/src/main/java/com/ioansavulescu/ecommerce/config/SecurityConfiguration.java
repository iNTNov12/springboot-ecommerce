package com.ioansavulescu.ecommerce.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // protejare endpoint /api/comenzi
        http.authorizeHttpRequests(requests ->
                requests
                        .requestMatchers("/api/comenzi/**")
                        .authenticated()
                        .anyRequest().permitAll())
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()));

        // + filtre CORS
        http.cors(Customizer.withDefaults());

        // strategie negociere continut
        http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());

        // raspuns pentru 401
        Okta.configureResourceServer401ResponseBody(http);

        // fara Cookie-uri pentru urmarirea sesiunii >> disable CSRF
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
