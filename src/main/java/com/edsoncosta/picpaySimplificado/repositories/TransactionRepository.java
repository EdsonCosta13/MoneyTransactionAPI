package com.edsoncosta.picpaySimplificado.repositories;

import com.edsoncosta.picpaySimplificado.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
