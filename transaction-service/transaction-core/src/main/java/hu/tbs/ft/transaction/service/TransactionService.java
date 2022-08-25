package hu.tbs.ft.transaction.service;

import hu.tbs.ft.pocket.PocketDTO;
import hu.tbs.ft.transaction.TransactionDTO;
import hu.tbs.ft.transaction.controller.TransactionMapper;
import hu.tbs.ft.transaction.model.Transaction;
import hu.tbs.ft.transaction.repository.TransactionRepository;
import hu.tbs.ft.transaction.util.TransactionException;
import hu.tbs.ft.user.model.dto.UserDTO;
import hu.tbs.ft.user.model.dto.UserServiceIF;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    private UserServiceIF userServiceIF;

    public List<TransactionDTO> findAllByPocketId(UUID pocketId) {
        return transactionRepository.findByPocketId(pocketId).stream().map(transactionMapper::transactionToTransactionDTO).collect(Collectors.toList());
    }

    public TransactionDTO createTransaction(TransactionDTO dto) throws TransactionException {
        UserDTO user = userServiceIF.findOneUser(dto.getUserId()).getBody();
        if (user.getId() == null || user.getId().equals("")) {
            throw new TransactionException("User is not existing");
        }

        // TODO pocket id keresés -> IF készítése
        PocketDTO pocket = new PocketDTO();
//        if (pocket.getId() == null || pocket.getId().equals("")) {
//            throw new TransactionException("Pocket is not existing");
//        }

        Transaction transaction = transactionMapper.transactionDtoToTransaction(dto);
        //transaction.setId(UUID.randomUUID());
        //transaction.setCreateDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        return transactionMapper.transactionToTransactionDTO(transaction);
    }

}
