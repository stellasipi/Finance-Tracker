package hu.tbs.ft.pocket.service;

import hu.tbs.ft.pocket.repository.PocketRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PocketService {

    @Autowired
    private PocketRepository pocketRepository;

}
