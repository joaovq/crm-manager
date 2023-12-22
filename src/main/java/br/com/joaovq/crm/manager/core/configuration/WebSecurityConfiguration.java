package br.com.joaovq.crm.manager.core.configuration;


import br.com.joaovq.crm.manager.core.security.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    @Value("${auth0.audience:http://localhost:9456}")
    private String audience;
    @Value("${jwt.accessTokenValidititySeconds:43200}") // 12 hours
    private int accessTokenValiditySeconds;
    @Value("${jwt.authorizedGrantTypes:password,authorization_code,refresh_token}")
    private String[] authorizedGrantTypes;

    @Value("${jwt.refreshTokenValiditySeconds:2592000}") // 30 days
    private int refreshTokenValiditySeconds;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**").disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(manager -> manager
                        .requestMatchers("/v1/api-docs/**", "/swagger-ui/**", "/h2-console/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/v1/token", "/v1/token/register")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/customer")
                        .hasAuthority("read")
                        .requestMatchers(HttpMethod.POST, "/v1/customer")
                        .hasAuthority("write")
                        .anyRequest()
                        .authenticated()
                )
//                .oauth2ResourceServer(
//        oauth2 -> oauth2.jwt(Customizer.withDefaults())
//                )
                .addFilterAt(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    JwtDecoder jwtDecoder() {
//        OAuth2TokenValidator<Jwt> withAudience = new AudienceValidator(audience);
//        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
//        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(withAudience, withIssuer);
//
//        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromOidcIssuerLocation(issuer);
//        jwtDecoder.setJwtValidator(validator);
//        return jwtDecoder;
//    }

}
