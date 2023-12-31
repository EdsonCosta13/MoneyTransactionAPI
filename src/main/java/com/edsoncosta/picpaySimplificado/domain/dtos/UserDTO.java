package com.edsoncosta.picpaySimplificado.domain.dtos;

import com.edsoncosta.picpaySimplificado.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(
        String firstName,
        String lastName, String document,
        String email,
        String password,
        BigDecimal balance

) {
}
