package com.mcb.transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcb.transaction.model.Transaction;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path = "/api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    //Fetch all

    @GetMapping(path = "/transaction")
    public ResponseEntity<List<Transaction>> gettAllTransaction(){
        try {
            List<Transaction> transactionList = transactionService.getAll();
            return new ResponseEntity<>(transactionList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //fetch by Id
    @GetMapping(path = "/transaction/{id}")
    public ResponseEntity<Transaction> fetchById(@PathVariable Integer id) {
        Optional<Transaction> transaction = transactionService.getById(id);

        if (transaction.isPresent()){
            return new ResponseEntity<Transaction> (transaction.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //create

    @PostMapping(path = "/transaction")
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) {
        try {
            Transaction addTransaction1 = transactionService.add(transaction);
            return new ResponseEntity<>(addTransaction1,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

