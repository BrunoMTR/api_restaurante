package com.example.api_restaurante.servicos;

import com.example.api_restaurante.modelos.Usuario;
import com.example.api_restaurante.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicoDetais implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email)  {

        Optional<Usuario>doador = usuarioRepository.findByEmail(email);

        return doador.orElse(null);

    }
}
