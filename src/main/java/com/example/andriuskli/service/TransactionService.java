package com.example.andriuskli.service;

import com.example.andriuskli.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactions();
    Transaction getTransaction(Long transactionId);
    void createTransaction(Transaction transaction);
}
