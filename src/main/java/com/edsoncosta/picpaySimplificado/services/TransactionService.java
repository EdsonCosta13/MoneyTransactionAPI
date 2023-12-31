package com.edsoncosta.picpaySimplificado.services;


import com.edsoncosta.picpaySimplificado.domain.user.User;
import com.edsoncosta.picpaySimplificado.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    private TransactionRepository transactionRepository;

    public void createTransaction(TransactioDTO data)
    {

    }



}
