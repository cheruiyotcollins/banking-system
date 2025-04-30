package com.dtb.payment.repository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByAccountId(String accountId);
    Optional<Transaction> findByIdAndAccountId(String id, String accountId);
}