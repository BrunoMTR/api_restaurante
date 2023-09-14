package com.example.api_restaurante.otp;

import com.example.api_restaurante.modelos.Produto;
import com.example.api_restaurante.otp.CategoriaDto;
import com.example.api_restaurante.otp.FornecedorDto;
import com.example.api_restaurante.otp.ImagemDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class ProdutoDto implements Serializable {
    private final String nome;
    private final String unidade_medida;
    private final double preco_unitario;
    private final Date validade;
    private final String descricao;
    private final String codigo;
    private final int quantidade;
    private final ImagemDto imagem;
    private final CategoriaDto categoria;
    private final FornecedorDto fornecedor;
}