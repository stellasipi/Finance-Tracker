package hu.tbs.ft.transaction.service;

import hu.tbs.ft.pocket.PocketDTO;
import hu.tbs.ft.pocket.PocketServiceIF;
import hu.tbs.ft.transaction.ModifyTransactionDTO;
import hu.tbs.ft.transaction.TransactionDTO;
import hu.tbs.ft.transaction.controller.TransactionMapper;
import hu.tbs.ft.transaction.model.Transaction;
import hu.tbs.ft.transaction.repository.TransactionRepository;
import hu.tbs.ft.transaction.util.PaymentType;
import hu.tbs.ft.transaction.util.ProceedType;
import hu.tbs.ft.transaction.util.TransactionException;
import hu.tbs.ft.user.model.dto.DbUser;
import hu.tbs.ft.user.model.dto.UserDTO;
import hu.tbs.ft.user.model.dto.UserServiceIF;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionalException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    private UserServiceIF userServiceIF;

    private PocketServiceIF pocketServiceIF;

    public List<TransactionDTO> findAllByPocketId(UUID pocketId, String username) throws TransactionException {
        log.debug("Get all transaction by pocket id {}", pocketId);
        UserDTO user = userServiceIF.findUserByUsername(username).getBody();
        ResponseEntity<PocketDTO> pocketResponse = pocketServiceIF.getPocketById(pocketId);
        if(pocketResponse.getStatusCode().isError()){
            throw new TransactionException("Pocket not existing");
        }
        PocketDTO pocket = pocketResponse.getBody();
        return transactionRepository.findByPocketId(pocketId).stream()
                .map(transaction -> setCreatorUsername(transactionMapper.transactionToTransactionDTO(transaction), transaction.getUserId()))
                .collect(Collectors.toList());
    }

    public TransactionDTO getTransactionById(UUID id, String username) throws TransactionException {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        UserDTO user = userServiceIF.findUserByUsername(username).getBody();

        if (transaction.isEmpty()) {
            log.debug("Transaction does not exists");
            throw new TransactionException("Transaction does not exists");
        }
        if (!transaction.get().getUserId().equals(user.getId())) {
            log.debug("The user is not the owner of the transaction");
            throw new TransactionException("The user is not the owner of the transaction");
        }

        return transactionMapper.transactionToTransactionDTO(transaction.get());

    }

    public TransactionDTO createTransaction(TransactionDTO dto) throws TransactionException {
        log.debug("Create new transaction");
        UserDTO user = userServiceIF.findOneUser(dto.getUserId()).getBody();
        if (user.getId() == null) {
            log.debug("Cannot find user with id: {}", dto.getUserId());
            throw new TransactionException("User is not existing");
        }

        PocketDTO pocket = pocketServiceIF.getPocketById(dto.getPocketId()).getBody();
        if (pocket.getId() == null) {
            log.debug("Cannot find pocket with id: {}", dto.getPocketId());
            throw new TransactionException("Pocket is not existing");
        }

        Transaction transaction = transactionMapper.transactionDtoToTransaction(dto);
        transactionRepository.save(transaction);
        log.debug("Transaction created: {}", transaction);

        return transactionMapper.transactionToTransactionDTO(transaction);
    }

    public TransactionDTO modifyTransaction(UUID id, ModifyTransactionDTO dto, String username) throws TransactionException {
        log.debug("Modify transaction");
        UserDTO user = userServiceIF.findUserByUsername(username).getBody();
        Optional<Transaction> originalTransaction = transactionRepository.findById(id);
        if (originalTransaction.isEmpty()) {
            log.debug("Cannot find transaction with id: {}", id);
            throw new TransactionException("Transaction is not exists");
        }
        if (!originalTransaction.get().getUserId().equals(user.getId())) {
            log.debug("The user is not the owner of the transaction");
            throw new TransactionException("The user is not the owner of the transaction");
        }
        Transaction transaction = originalTransaction.get();

        transaction.setAmount(dto.getAmount().equals(transaction.getAmount().doubleValue()) ? transaction.getAmount() : BigDecimal.valueOf(dto.getAmount()));
        transaction.setDescription(dto.getDescription());
        transaction.setName(dto.getName().equals(transaction.getName()) ? transaction.getName() : dto.getName());
        transaction.setPaymentType(PaymentType.valueOf(dto.getPaymentType()).equals(transaction.getPaymentType()) ? transaction.getPaymentType() : PaymentType.valueOf(dto.getPaymentType()));
        transaction.setPocketId(dto.getPocketId().equals(transaction.getPocketId()) ? transaction.getPocketId() : dto.getPocketId());
        transaction.setProceedType(ProceedType.valueOf(dto.getProceedType()).equals(transaction.getProceedType()) ? transaction.getProceedType() : ProceedType.valueOf(dto.getProceedType()));
        transactionRepository.save(transaction);
        log.debug("Transaction ({}) have been modified", transaction.getId());

        return transactionMapper.transactionToTransactionDTO(transaction);
    }

    public void deleteTransaction(UUID id, String username) throws TransactionException {
        UserDTO user = userServiceIF.findUserByUsername(username).getBody();
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isEmpty()) {
            log.debug("Transaction is not existing");
            throw new TransactionException("Transaction is not existing");
        }
        if (!transaction.get().getUserId().equals(user.getId())) {
            log.debug("The user is not the owner of the transaction");
            throw new TransactionException("The user is not the owner of the transaction");
        }
        transactionRepository.deleteById(transaction.get().getId());
        log.debug("Transaction has been deleted");
    }

    private TransactionDTO setCreatorUsername(TransactionDTO transaction, UUID userId){
        ResponseEntity<UserDTO> userResponse = userServiceIF.findOneUser(userId);
        if(!userResponse.getStatusCode().isError()){
            transaction.setCreatorUsername(userResponse.getBody().getName());
        }
        return transaction;
    }

}
