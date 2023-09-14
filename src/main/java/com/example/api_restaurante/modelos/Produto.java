package com.example.api_restaurante.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String unidade_medida;
    private double preco_unitario;
    private Date validade;
    private String descricao;
    private String codigo;
    private int quantidade;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagem_id", referencedColumnName = "id")
    private Imagem imagem;


    @ManyToOne
    @JoinColumn(name="categoria_id", nullable=false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name="fornecedor_id")
    private Fornecedor fornecedor;
}
