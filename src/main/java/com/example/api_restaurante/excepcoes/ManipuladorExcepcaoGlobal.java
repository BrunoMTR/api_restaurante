package com.example.api_restaurante.excepcoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ManipuladorExcepcaoGlobal {
    @ExceptionHandler(value = {ErroUsuario.class})
    public ResponseEntity<Object> erroUsuario(ErroUsuario erroUsuario){
        Excepcao excepcao = new Excepcao(erroUsuario.getMessage(),new Date(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(excepcao,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ErroConversaoDados.class})
    public ResponseEntity<Object>erroConversaoDados(ErroConversaoDados erroConversaoDados){
        Excepcao excepcao = new Excepcao(erroConversaoDados.getMessage(),new Date(),HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(excepcao,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
