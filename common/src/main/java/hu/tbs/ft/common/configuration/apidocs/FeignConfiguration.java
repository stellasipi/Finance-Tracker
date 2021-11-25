package hu.tbs.ft.common.configuration.apidocs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
@EnableFeignClients(basePackages = "hu.tbs.ft")
public class FeignConfiguration {

    @Value("${finance-tracker.auth-sever-url}")
    private String authServerUrl;

    @Value("${finance-tracker.security.client-id}")
    private String clientId;

    @Value("${finance-tracker.security.client-secret}")
    private String clientSecret;

    @Bean
    public OAuth2FeignRequestInterceptor oauth2schemeRequestInterceptor() { //kimenő feign hívások oatuh2 hívások lesznek
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), oauth2schemeResourceDetails());
    }

    @Bean
    public ClientCredentialsResourceDetails oauth2schemeResourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(authServerUrl + "/token");
        return details;
    }
}
