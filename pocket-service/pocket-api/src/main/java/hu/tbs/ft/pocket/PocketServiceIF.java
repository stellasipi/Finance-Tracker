package hu.tbs.ft.pocket;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "pocket-service", url = "${services.pocket-service-url:localhost:8084}/pocket")
public interface PocketServiceIF {

    @GetMapping("/{id}")
    ResponseEntity<PocketDTO> getPocketById(@PathVariable UUID id);

}
