package com.edsoncosta.picpaySimplificado.repositories;

import com.edsoncosta.picpaySimplificado.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserDocument(String document);
    Optional<User> findUserById(Long id);
}
