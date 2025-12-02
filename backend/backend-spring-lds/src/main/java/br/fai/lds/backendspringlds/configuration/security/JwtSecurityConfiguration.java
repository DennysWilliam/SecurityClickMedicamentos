package br.fai.lds.backendspringlds.configuration.security;

import br.fai.lds.backendspringlds.service.authentication.jwt.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class JwtSecurityConfiguration {

  private final JwtRequestFilter jwtRequestFilter;

  public JwtSecurityConfiguration(JwtRequestFilter jwtRequestFilter) {
    this.jwtRequestFilter = jwtRequestFilter;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.applyPermitDefaultValues();
    configuration.setAllowedMethods(List.of("GET", "POST", "DELETE", "OPTIONS", "HEAD", "PUT"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/authenticate",
                    "/api/user" // Permitir cadastro
            ).permitAll()

            .antMatchers(HttpMethod.GET, "/api/user/**")
            .hasAnyAuthority("ROLE_ADMINISTRATOR", "ROLE_USER")

            .antMatchers(HttpMethod.PUT, "/api/user/**")
            .hasAuthority("ROLE_USER")

            .antMatchers(HttpMethod.DELETE, "/api/user/**")
            .hasAuthority("ROLE_ADMINISTRATOR")

            .anyRequest().authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .headers()
            .frameOptions().sameOrigin()
            .and()
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }


}
