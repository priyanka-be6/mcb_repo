package com.mcb;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mcb.transaction.TransactionRepository;
import com.mcb.transaction.TransactionService;
import com.mcb.transaction.model.Transaction;

@SpringBootTest
public class TransactionServiceUT {

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;


    @Test
    public void getTransactionsTests() {
        when(transactionRepository.findAll()).thenReturn(Stream.of(new Transaction(1,
                                "Active","red76jf",786978,"NayamUser",
                                898989889,1000.0,"Rupee","MCB",
                                797979787,"Success","CreditCard","India"),
                        new Transaction(2,
                                "Active","red76jf",786978,"MCBUser",
                                898989889,1000.0,"Rupee","MCB",
                                797979787,"Success","CreditCard","India"))
                .collect(Collectors.toList()));
        assertEquals(2,transactionService.getAll().size());

    }

    @Test
    public void getTransactionByIdTests(){
        int id = 1;
        Optional<Transaction> transaction = Optional.of(new Transaction(1,
                "Active", "red76jf", 786978, "NayamUser",
                898989889, 1000.0, "Rupee", "MCB",
                797979787, "Success", "CreditCard", "India"));

        when(transactionRepository.findById(id)).thenReturn(transaction);
        Assert.assertEquals(transaction,transactionService.getById(id));
    }

    @Test
    public void saveTransactionTest(){
        Transaction transaction = new Transaction(1,
                "Active","red76jf",786978,"NayamUser",
                898989889,1000.0,"Rupee","MCB",
                797979787,"Success","CreditCard","India");

        when(transactionRepository.save(transaction)).thenReturn(transaction);
        Assert.assertEquals(transaction,transactionService.add(transaction));
    }
}

