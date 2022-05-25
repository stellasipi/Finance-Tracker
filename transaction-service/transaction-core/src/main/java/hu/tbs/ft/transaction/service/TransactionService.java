package hu.tbs.ft.transaction.service;

import hu.tbs.ft.transaction.TransactionDTO;
import hu.tbs.ft.transaction.controller.TransactionMapper;
import hu.tbs.ft.transaction.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    public List<TransactionDTO> findAllByPocketId(UUID pocketId) {
        return transactionRepository.findByPocketId(pocketId).stream().map(transactionMapper::transactionToTransactionDTO).collect(Collectors.toList());
    }

}
