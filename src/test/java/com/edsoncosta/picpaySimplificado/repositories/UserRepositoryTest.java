package com.edsoncosta.picpaySimplificado.repositories;

import com.edsoncosta.picpaySimplificado.domain.dtos.UserDTO;
import com.edsoncosta.picpaySimplificado.domain.user.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;




@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {


    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("should get user successfull from db")
    void findByDocumentCase1() {
        String document="987654321LA909";
        UserDTO newUser=new UserDTO("Eufranio ","Diogo",document,"eufranio@gmail.com","123456",new BigDecimal(260));

        this.createUser(newUser);

        Optional<User> result=this.userRepository.findByDocument(document);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("should not get user from db when user not exists")
    void findByDocumentCase2() {
        String document="987654321LA909";


        Optional<User> result=this.userRepository.findByDocument(document);

        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(UserDTO data)
    {
        User newUser=new User(data);
        this.entityManager.persist(newUser);

        return newUser;
    }
}