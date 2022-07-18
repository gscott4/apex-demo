package com.example.apexdemo.controller;

import com.example.apexdemo.entity.TransactionEntity;
import com.example.apexdemo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class TransactionController {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/transactions")
    @ResponseBody
    public List<TransactionEntity> findAllTransactions() {
        return transactionRepository.findAll();
    }

    @PostMapping("/transactions")
    ResponseEntity<Object> createTransaction(@RequestBody TransactionEntity transactionEntity) {
        TransactionEntity savedTransaction = this.transactionRepository.save(transactionEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTransaction.getTransactionId()).toUri();

        return ResponseEntity.created(location).build();

    }

}
