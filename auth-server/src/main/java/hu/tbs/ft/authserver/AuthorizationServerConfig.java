package hu.tbs.ft.authserver;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import hu.tbs.ft.authserver.jwt.Jwks;
import io.swagger.models.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.web.OAuth2TokenEndpointFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AuthorizationServerConfig {

    private static RequestMatcher tokenEndpointMatcher() {
        return new AntPathRequestMatcher(
                OAuth2TokenEndpointFilter.DEFAULT_TOKEN_ENDPOINT_URI,
                HttpMethod.POST.name());
    }

    @Bean
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();

        List<RequestMatcher> requestMatchers = new ArrayList<>();
        requestMatchers.add(authorizationServerConfigurer.getEndpointsMatcher());
        requestMatchers.add(new AntPathRequestMatcher(OAuth2TokenEndpointFilter.DEFAULT_TOKEN_ENDPOINT_URI, HttpMethod.OPTIONS.name()));

        http
                .cors().and()
                .requestMatcher(new OrRequestMatcher(requestMatchers))
                .authorizeRequests((authorizeRequests) -> {
                    ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) authorizeRequests.anyRequest()).authenticated();
                })
                .csrf((csrf) -> {
                    csrf.ignoringRequestMatchers(tokenEndpointMatcher());
                })
                .apply(authorizationServerConfigurer);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository() {

        RegisteredClient frontendClient = RegisteredClient.withId("08f40fbb-7d81-44a0-8f2c-47a14cf3e719")
                .clientId("finance-tracker-frontend")
                .clientSecret("74843dc8-6e01-414b-9231-10381e718203")
                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://127.0.0.1:4200")
                .redirectUri("http://127.0.0.1:8080/swagger-ui/oauth2-redirect.html")
                .redirectUri("http://127.0.0.1:8080/swagger-ui/oauth2-redirect.html")
                .build();

        RegisteredClient apiGateway = RegisteredClient.withId("c5c492e8-d921-4e49-9a6c-14a120350be0")
                .clientId("api-gateway")
                .clientSecret("090d6b10-9258-46b5-830f-7b9c4fd07e00")
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build();

        RegisteredClient planningService = RegisteredClient.withId("cb9cebc7-1102-4003-96ca-4b3c69ce0e93")
                .clientId("planning-service")
                .clientSecret("1d8fc149-f130-4a4a-bfe9-3f36e11e87cc")
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build();

        RegisteredClient pocketService = RegisteredClient.withId("5090cbcf-e1ad-443b-9e87-94784aa5c21e")
                .clientId("pocket-service")
                .clientSecret("d0560d07-0480-4794-ae6c-c6061e391a53")
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build();

        RegisteredClient reportService = RegisteredClient.withId("2b4ea1b9-b4f7-40a3-b064-3c72973b35b1")
                .clientId("report-service")
                .clientSecret("a2a51da0-20a1-4f47-8227-e48fd8657238")
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build();

        RegisteredClient transactionService = RegisteredClient.withId("b9333eb6-82ee-4ff5-99e9-8a04f122a39d")
                .clientId("transaction-service")
                .clientSecret("ea4723c1-5fd4-4f64-adf6-5bb283f3a52b")
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build();

        RegisteredClient userService = RegisteredClient.withId("ee4812a6-a350-4dce-b592-ea84fcfdca8a")
                .clientId("user-service")
                .clientSecret("c4319f86-0dbc-4915-b204-e6d4f21279b9")
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build();

        return new InMemoryRegisteredClientRepository(frontendClient, apiGateway, planningService, pocketService, reportService, transactionService, userService);
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = Jwks.generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }
}
