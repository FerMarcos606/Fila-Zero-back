package com.filazero.demo.security;

import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope;

import java.util.Arrays;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.filazero.demo.customer.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;


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
                .requestMatchers(HttpMethod.POST, "/v1/api/auth/token").permitAll()

                .requestMatchers(HttpMethod.POST, endpoint + "/auth/login").permitAll()

                .requestMatchers(HttpMethod.GET, endpoint + "/customers/**").access(hasScope("READ"))
                .requestMatchers(HttpMethod.PUT, endpoint + "/customers/**").access(hasScope("WRITE"))
                .requestMatchers(HttpMethod.POST, endpoint + "/deliveries/**").access(hasScope("WRITE"))
                .requestMatchers(HttpMethod.GET, endpoint + "/deliveries/**").access(hasScope("READ"))
                .requestMatchers(HttpMethod.PUT, endpoint + "/deliveries/**").access(hasScope("WRITE"))
                .requestMatchers(HttpMethod.DELETE, endpoint + "/deliveries/**").access(hasScope("WRITE"))
                .requestMatchers(HttpMethod.GET, endpoint + "/profiles/**").access(hasScope("READ"))
                .requestMatchers(HttpMethod.PUT, endpoint + "/profiles/**").access(hasScope("WRITE"))
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .oauth2ResourceServer(oauth -> oauth.jwt(jwt -> jwt.decoder(jwtDecoder())));

        return http.build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(key.getBytes()));
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] bytes = key.getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(bytes, 0, bytes.length, "HmacSHA512"); // ⚡ cambio aquí
        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS512).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
