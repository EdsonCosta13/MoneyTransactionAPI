package com.edsoncosta.picpaySimplificado.controllers;


import com.edsoncosta.picpaySimplificado.domain.dtos.UserDTO;
import com.edsoncosta.picpaySimplificado.domain.user.User;
import com.edsoncosta.picpaySimplificado.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO data)
    {
        User newUser=userService.createUser( data);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> users=this.userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

}
