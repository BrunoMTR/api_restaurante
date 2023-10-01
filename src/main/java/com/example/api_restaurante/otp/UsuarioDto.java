package com.example.api_restaurante.otp;

import com.example.api_restaurante.modelos.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto implements Serializable {
    private  String nome;
    private  String sobrenome;
    private  String email;
    private  String passe;
    private  Role role;
    private  RestauranteDto restaurante;
}