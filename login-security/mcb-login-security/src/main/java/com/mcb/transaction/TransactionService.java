package com.mcb.transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcb.transaction.model.Transaction;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    //get all
    public List<Transaction> getAll() {
        List<Transaction> transactionList  = transactionRepository.findAll();
        return transactionList;
    }

    //Transaction By id;

    public Optional<Transaction> getById(Integer id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction;
    }

    //create
    public Transaction add(Transaction transaction) {
        Transaction createTransaction = transactionRepository.save(transaction);
        return createTransaction;
    }
}


