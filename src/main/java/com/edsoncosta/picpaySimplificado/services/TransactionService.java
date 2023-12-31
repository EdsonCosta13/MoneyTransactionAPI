package com.edsoncosta.picpaySimplificado.services;


import com.edsoncosta.picpaySimplificado.domain.dtos.TransactionDTO;
import com.edsoncosta.picpaySimplificado.domain.transaction.Transaction;
import com.edsoncosta.picpaySimplificado.domain.user.User;
import com.edsoncosta.picpaySimplificado.repositories.TransactionRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void createTransaction(TransactionDTO data) throws Exception {
        User sender=this.userService.findUserById(data.senderId());
        User receiver=this.userService.findUserById(data.receiverId());

        userService.validateTransaction(sender,data.value());

        boolean isAuthorized=this.authorizeTransaction(sender,data.value());
        if(!isAuthorized)
        {
            throw new Exception("Transaction not authorized!");
        }

        Transaction transaction=new Transaction();
        transaction.setAmount(data.value());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(data.value()));
        receiver.setBalance(receiver.getBalance().add(data.value()));

        this.transactionRepository.save(transaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);
    }

    public boolean authorizeTransaction(User sender, BigDecimal value)
    {
        String url="https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";
        ResponseEntity<Map> authorizationResponse=restTemplate.getForEntity(url, Map.class);

        if(authorizationResponse.getStatusCode()== HttpStatus.OK)
        {
            String message=(String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);

        }else return false;
    }



}
