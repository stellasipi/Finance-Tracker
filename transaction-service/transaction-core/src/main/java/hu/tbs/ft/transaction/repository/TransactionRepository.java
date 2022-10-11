package hu.tbs.ft.transaction.repository;

import hu.tbs.ft.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByPocketIdAndUserId(UUID pocketId, UUID userId);

    List<Transaction> findByPocketId(UUID pocketId);
}
