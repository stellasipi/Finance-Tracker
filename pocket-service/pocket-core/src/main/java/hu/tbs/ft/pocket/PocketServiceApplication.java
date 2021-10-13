package hu.tbs.ft.pocket;

import hu.tbs.ft.common.configuration.apidocs.ApiDocsConfiguration;
import hu.tbs.ft.common.configuration.apidocs.ResourceServerWebSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ApiDocsConfiguration.class, ResourceServerWebSecurityConfig.class})
@Slf4j
public class PocketServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PocketServiceApplication.class, args);
        log.info("PocketService has successfully started!");
    }
}
