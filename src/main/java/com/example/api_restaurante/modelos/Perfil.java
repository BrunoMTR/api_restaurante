package com.example.api_restaurante.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String url;

    @OneToOne(mappedBy = "perfil", cascade = CascadeType.ALL)
    private Usuario usuario;
}
