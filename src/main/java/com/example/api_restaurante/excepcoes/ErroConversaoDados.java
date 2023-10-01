package com.example.api_restaurante.excepcoes;

public class ErroConversaoDados extends RuntimeException{
    public ErroConversaoDados(String message) {
        super(message);
    }
}