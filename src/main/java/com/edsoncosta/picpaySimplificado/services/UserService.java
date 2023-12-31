package com.edsoncosta.picpaySimplificado.services;


import com.edsoncosta.picpaySimplificado.domain.dtos.UserDTO;
import com.edsoncosta.picpaySimplificado.domain.user.User;
import com.edsoncosta.picpaySimplificado.domain.user.UserType;
import com.edsoncosta.picpaySimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType()== UserType.MERCHANT)
        {
            throw new Exception("User not permited to make transactions!");
        }

        if(sender.getBalance().compareTo(amount)<0)
        {
            throw new Exception("Saldo insuficiente!");
        }
    }

    public User findUserById(Long id) throws Exception
    {
        return this.userRepository.
                findUserById(id).
                orElseThrow(()->new Exception("User not found!"));
    }

    public User createUser(UserDTO data)
    {
        User newUser=new User(data);
        this.saveUser(newUser);

        return newUser;
    }

    public void saveUser(User user)
    {
        this.userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
