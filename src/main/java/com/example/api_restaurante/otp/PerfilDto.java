package com.example.api_restaurante.otp;

import com.example.api_restaurante.modelos.Perfil;
import lombok.Data;

import java.io.Serializable;


@Data
public class PerfilDto implements Serializable {
    private final String url;
}