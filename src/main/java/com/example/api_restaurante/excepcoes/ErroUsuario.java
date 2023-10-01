package com.example.api_restaurante.excepcoes;

public class ErroUsuario extends RuntimeException{
    public ErroUsuario(String message) {
        super(message);
    }
}
