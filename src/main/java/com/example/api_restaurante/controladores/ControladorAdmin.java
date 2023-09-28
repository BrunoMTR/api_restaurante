package com.example.api_restaurante.controladores;

import com.example.api_restaurante.modelos.Token;
import com.example.api_restaurante.servicos.ServicoAdministrador;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMINISTRADOR')")
public class ControladorAdmin {
    private final ServicoAdministrador servicoAdministrador;
    @PostMapping("/registrar")
    @PreAuthorize("hasAuthority('administrador:ler')")
    public ResponseEntity<Token> registrar(@RequestParam("usuario") String usuario){
        return servicoAdministrador.registrar(usuario);
    }

    @PreAuthorize("hasAuthority('administrador:ler')")
    @GetMapping("/x")
    public String x (){
        return "ollaa";
    }

}
