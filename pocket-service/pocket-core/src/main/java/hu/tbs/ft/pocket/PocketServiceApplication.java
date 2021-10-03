package hu.tbs.ft.pocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PocketServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PocketServiceApplication.class, args);
        log.info("PocketService has successfully started!");
    }
}
