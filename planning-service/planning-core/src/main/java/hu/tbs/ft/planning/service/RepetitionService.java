package hu.tbs.ft.planning.service;

import hu.tbs.ft.planning.repository.RepetitionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RepetitionService {

    private RepetitionRepository repetitionRepository;

}
