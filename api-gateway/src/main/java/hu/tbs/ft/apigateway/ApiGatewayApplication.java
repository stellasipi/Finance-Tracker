package hu.tbs.ft.apigateway;

import hu.tbs.ft.common.configuration.apidocs.ApiDocsConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Import;

@EnableZuulProxy
@SpringBootApplication
@Import(ApiDocsConfiguration.class)
@Slf4j
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
        log.info("API Gateway has successfully started!");
    }
}