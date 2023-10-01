package com.example.api_restaurante.servicos;

import com.example.api_restaurante.excepcoes.ErroConversaoDados;
import com.example.api_restaurante.modelos.Role;
import com.example.api_restaurante.modelos.Token;
import com.example.api_restaurante.modelos.Usuario;
import com.example.api_restaurante.otp.UsuarioDto;
import com.example.api_restaurante.repositorios.RestauranteRepository;
import com.example.api_restaurante.repositorios.TokenRepository;
import com.example.api_restaurante.repositorios.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoAdministrador {

    private final Mapeador mapeador;
    private final PasswordEncoder criptografo;

    private final TokenRepository tokenRepository;

    private final UsuarioRepository usuarioRepository;

    private final RestauranteRepository restauranteRepository;


    public ResponseEntity<Token> registrar(String objecto)  {
        try{
            //Mapeamento dos parametros recebidos para os respectivo objectos
            ObjectMapper objectMapper = new ObjectMapper();
            Usuario usuarioTemporal = objectMapper.readValue(objecto,Usuario.class);
            System.out.println(usuarioTemporal);

            if(validarEmail(usuarioTemporal.getEmail())){
                usuarioTemporal.setRole(Role.ADMINISTRADOR);
                usuarioTemporal.setPasse(criptografo.encode(usuarioTemporal.getPasse()));
                usuarioTemporal.setRestaurante(restauranteRepository.findById(1L).get());

                //criando o token
                Token tokenTemporal =  UtilidadesToken.gerarToken(usuarioTemporal);



                Usuario usuario = usuarioRepository.save(usuarioTemporal);
                ///////////////////////////////////////


                tokenTemporal.setUsuario(usuario);
                Token token = tokenRepository.save(tokenTemporal);


                List<Token>listaTokens = new ArrayList<>();
                listaTokens.add(token);
                /////////////////////////////////////////

                usuarioTemporal.setTokens(listaTokens);
                ////////////////////////////////////////
                //Mapeando
                UsuarioDto usuarioDto = mapeador.converterParaDTO(UsuarioDto.class,usuario);
                //return token;
                return ResponseEntity.status(HttpStatus.CREATED).body(token);
            }
        }catch (Exception erro){
            throw new ErroConversaoDados("Impossivel converter objecto para Usuario: " + erro);
        }
        return null;
    }

    public boolean  validarEmail(String email){
        return usuarioRepository.findByEmail(email).isEmpty();
    }
}
