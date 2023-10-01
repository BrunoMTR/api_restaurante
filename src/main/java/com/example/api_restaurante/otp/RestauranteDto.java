package com.example.api_restaurante.otp;

import com.example.api_restaurante.modelos.Restaurante;
import lombok.Data;

import java.io.Serializable;


@Data
public class RestauranteDto implements Serializable {
    private final String nome;
    private final String endereco;
    private final String contacto;
}