package hu.tbs.ft.transaction.service;

import hu.tbs.ft.transaction.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

}
