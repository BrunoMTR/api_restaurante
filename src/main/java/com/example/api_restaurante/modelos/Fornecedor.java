package com.example.api_restaurante.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String contacto;
    private String endereco;

    @OneToMany(mappedBy="fornecedor")
    private Set<Produto>produtos;


}
