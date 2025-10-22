package com.filazero.demo.Security;
import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.filazero.demo.customer.CustomerRepository;
import com.nimbusds.jose.jwk.source.ImmutableSecret;

import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
@Configuration
public class SecurityConfiguration {

    @Value("${jwt.key}")
    private String key;

    @Value("${api-endpoint}")
    private String endpoint;

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .cors(cors -> cors.configurationSource(corsConfiguration()))
        .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**").disable())
        .headers(header -> header.frameOptions(frame -> frame.sameOrigin()))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/h2-console/**").permitAll()
            .requestMatchers(HttpMethod.POST, endpoint + "/register").permitAll()
            .requestMatchers(HttpMethod.GET, endpoint + "/login").hasAnyRole("CUSTOMER", "ADMIN")
            .requestMatchers(HttpMethod.POST, endpoint + "/customers").permitAll()
            .requestMatchers(HttpMethod.GET, endpoint + "/products/**").permitAll()
            .requestMatchers(HttpMethod.GET, endpoint + "/customers/**").access(hasScope("READ"))
            .requestMatchers(HttpMethod.PUT, endpoint + "/customers/**").access(hasScope("WRITE"))
            .requestMatchers(HttpMethod.POST, endpoint + "/deliveries/**").access(hasScope("WRITE"))
            .requestMatchers(HttpMethod.GET, endpoint + "/deliveries/**").access(hasScope("READ"))
            .requestMatchers(HttpMethod.PUT, endpoint + "/deliveries/**").access(hasScope("WRITE"))
            .requestMatchers(HttpMethod.DELETE, endpoint + "/deliveries/**").access(hasScope("WRITE"))
            .requestMatchers(HttpMethod.GET, endpoint + "/profiles/**").access(hasScope("READ"))
            .requestMatchers(HttpMethod.PUT, endpoint + "/profiles/**").access(hasScope("WRITE"))
            .requestMatchers(HttpMethod.POST, endpoint + "/products/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, endpoint + "/products/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, endpoint + "/products/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST, endpoint + "/payments/**").access(hasScope("WRITE"))
            .requestMatchers(HttpMethod.GET, endpoint + "/turns/**").access(hasScope("READ"))
            .requestMatchers(HttpMethod.GET, endpoint + "/notifications/**").access(hasScope("READ"))
            .anyRequest().authenticated()
        )
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .oauth2ResourceServer(oauth -> oauth.jwt(jwt -> jwt.decoder(jwtDecoder())));

    return http.build();
}


    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] bytes = key.getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(bytes, 0, bytes.length, "HmacSHA512");
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }

    @Bean
    CorsConfigurationSource corsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(CustomerRepository customerRepository) {
        return new UserDetailsServiceImpl(customerRepository);
    }
}
