package hu.tbs.ft.transaction.service;

import hu.tbs.ft.transaction.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService {

    private TransactionRepository transactionRepository;

}
