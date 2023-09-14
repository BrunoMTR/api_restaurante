package com.example.api_restaurante.otp;

import com.example.api_restaurante.modelos.Role;
import lombok.Data;

import java.io.Serializable;


@Data
public class UsuarioDto implements Serializable {
    private final String nome;
    private final String sobrenome;
    private final String email;
    private final String passe;
    private final Role role;
    private final PerfilDto perfil;
    private final RestauranteDto restaurante;
}