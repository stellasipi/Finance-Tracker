package hu.tbs.ft.transaction.controller;

import hu.tbs.ft.transaction.TransactionDTO;
import hu.tbs.ft.transaction.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDTO transactionToTransactionDTO(Transaction transaction);

    Transaction transactionDtoToTransaction(TransactionDTO transactionDTO);
}
