package hu.tbs.ft.planning;

import hu.tbs.ft.common.configuration.apidocs.ApiDocsConfiguration;
import hu.tbs.ft.common.configuration.apidocs.FeignConfiguration;
import hu.tbs.ft.common.configuration.apidocs.ResourceServerWebSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ApiDocsConfiguration.class, ResourceServerWebSecurityConfig.class, FeignConfiguration.class})
@Slf4j
public class PlanningServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlanningServiceApplication.class, args);
        log.info("PlanningService has successfully started!");
    }
}
