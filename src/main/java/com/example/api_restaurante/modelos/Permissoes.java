package com.example.api_restaurante.modelos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permissoes {

    ADMINISTRADOR_READ("administrador:ler"),
    ADMINISTRADOR_UPDATE("administrador:atualizar"),
    ADMINISTRADOR_CREATE("administrador:criar"),
    ADMINISTRADOR_DELETE("administrador:apagar"),


    GESTOR_READ("gestor:ler"),
    GESTOR_UPDATE("gestor:atualizar"),
    GESTOR_CREATE("gestor:criar"),
    GESTOR_DELETE("gestor:apagar"),


    FUNCIONARIO_READ("funcionario:ler"),
    FUNCIONARIO_UPDATE("funcionario:atualizar"),
    FUNCIONARIO_CREATE("funcionario:criar"),
    FUNCIONARIO_DELETE("funcionario:apagar");




    @Getter
    private final String permissoes;
}

