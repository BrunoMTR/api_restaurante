package com.example.api_restaurante.controladores;

import com.example.api_restaurante.modelos.Token;
import com.example.api_restaurante.seguranca.Utilizador;
import com.example.api_restaurante.servicos.ServicoAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
//@Tag(name = "Genêrico")
public class ControladorAuth {
    private final ServicoAuth servicoAuth;



    @PostMapping("/logar")
    public Token autenticar(@RequestBody Utilizador utilizador){
        return servicoAuth.autenticar(utilizador);
    }
}
