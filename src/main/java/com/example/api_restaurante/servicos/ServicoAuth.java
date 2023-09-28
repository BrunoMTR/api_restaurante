package com.example.api_restaurante.servicos;

import com.example.api_restaurante.excepcoes.ErroUsuario;
import com.example.api_restaurante.modelos.Token;
import com.example.api_restaurante.modelos.Usuario;
import com.example.api_restaurante.repositorios.UsuarioRepository;
import com.example.api_restaurante.seguranca.Utilizador;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicoAuth {
    private final UsuarioRepository usuarioRepository;
    private final UtilidadesToken utilidadesToken;
    private final PasswordEncoder criptografo;
    private final AuthenticationManager authenticationManager;
    public Token autenticar(Utilizador utilizador) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(utilizador.getEmail());


        Token token = null;
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            token = UtilidadesToken.gerarToken(usuario);
            utilidadesToken.revogarTokens(usuario);

            Authentication autenticacao = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            utilizador.getEmail(),
                            utilizador.getPalavra_passe()
                    )

            );

            return utilidadesToken.salvarToken(usuario, token);

        }
        throw new ErroUsuario("Usuario não cadastrado");

    }
}
