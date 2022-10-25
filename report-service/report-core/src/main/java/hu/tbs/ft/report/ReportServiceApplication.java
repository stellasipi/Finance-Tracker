package hu.tbs.ft.report;

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
public class ReportServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReportServiceApplication.class, args);
        log.info("ReportService has successfully started!");
    }
}
