package com.tfg.tfg.config;

import com.tfg.tfg.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final UserService userService;
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider prov = new DaoAuthenticationProvider();
        prov.setUserDetailsService(userService);
        prov.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(prov);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedOriginPatterns(List.of("https://localhost:5175", "http://localhost:5175","https://localhost:5176", "http://localhost:5176", "https://localhost:3000", "http://localhost:3000", "http://13.39.149.35:3000", "https://13.39.149.35:3000", "https://tfgmyke.duckdns.org/", "http://tfgmyke.duckdns.org/"));
        cfg.setAllowedMethods(List.of("GET", "POST", "DELETE", "OPTIONS"));
        cfg.setAllowedHeaders(List.of("*"));
        cfg.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
        src.registerCorsConfiguration("/**", cfg);
        return src;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )
                .authorizeHttpRequests(auth -> auth
                        // Preflight CORS
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Endpoints anónimos
                        .requestMatchers(HttpMethod.POST,
                                "/minecraftProject/auth/register",
                                "/minecraftProject/auth/login",
                                "/minecraftProject/auth/logout"  // <–– abrimos logout
                        ).permitAll()

                        // Búsquedas públicas
                        .requestMatchers(HttpMethod.GET,  "/minecraftProject/structures/types").permitAll()
                        .requestMatchers(HttpMethod.POST, "/minecraftProject/structures/search").permitAll()

                        .requestMatchers(HttpMethod.GET, "/minecraftProject/tiles").permitAll()

                        // Seeds protegidos
                        .requestMatchers("/minecraftProject/seeds/**").authenticated()

                        .requestMatchers(HttpMethod.GET, "/minecraftProject/world/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/minecraftProject/world/**").permitAll()

                        // Resto → 403
                        .anyRequest().denyAll()
                )
                .formLogin(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

