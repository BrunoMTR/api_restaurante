package com.example.api_restaurante.otp;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.api_restaurante.modelos.Fornecedor} entity
 */
@Data
public class FornecedorDto implements Serializable {
    private final String nome;
    private final String contacto;
    private final String endereco;
}