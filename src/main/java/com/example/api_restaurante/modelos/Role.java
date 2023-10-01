package com.example.api_restaurante.modelos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    ADMINISTRADOR(
            Set.of(
                    Permissoes.ADMINISTRADOR_READ,
                    Permissoes.ADMINISTRADOR_UPDATE,
                    Permissoes.ADMINISTRADOR_CREATE,
                    Permissoes.ADMINISTRADOR_DELETE
            )
    ),
    GESTOR(
            Set.of(
                    Permissoes.GESTOR_READ,
                    Permissoes.GESTOR_UPDATE,
                    Permissoes.GESTOR_CREATE,
                    Permissoes.GESTOR_DELETE
            )
    ),
    FUNCIONARIO(
            Set.of(
                    Permissoes.FUNCIONARIO_READ,
                    Permissoes.FUNCIONARIO_UPDATE,
                    Permissoes.FUNCIONARIO_CREATE,
                    Permissoes.FUNCIONARIO_DELETE
            )
    )
    ;

    @Getter
    private final Set<Permissoes>permissoes;


    public List<SimpleGrantedAuthority> buscarAutoridades(){
        var autoridades =  getPermissoes()
                .stream().map(permissao->new SimpleGrantedAuthority(permissao.getPermissoes())).collect(Collectors.toList());
        autoridades.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return autoridades;
    }
}

