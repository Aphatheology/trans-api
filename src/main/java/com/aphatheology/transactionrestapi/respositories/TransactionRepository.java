package com.aphatheology.transactionrestapi.respositories;

import com.aphatheology.transactionrestapi.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
