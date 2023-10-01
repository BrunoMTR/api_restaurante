package com.example.api_restaurante.excepcoes;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
public class Excepcao {
    private String mensagem;
    private Date data;
    private HttpStatus status;
}

