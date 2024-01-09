package com.edsoncosta.picpaySimplificado.repositories;

import com.edsoncosta.picpaySimplificado.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByDocument(String document);
    //Optional<User> findUserById(Long id);
}
