package com.edsoncosta.picpaySimplificado.controllers;


import com.edsoncosta.picpaySimplificado.domain.dtos.TransactionDTO;
import com.edsoncosta.picpaySimplificado.domain.transaction.Transaction;
import com.edsoncosta.picpaySimplificado.services.TransactionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO data) throws Exception {
        Transaction newTransaction=this.transactionService.createTransaction(data);

        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }
}
