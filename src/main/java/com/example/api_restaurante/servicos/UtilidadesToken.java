package com.example.api_restaurante.servicos;

import com.example.api_restaurante.modelos.Token;
import com.example.api_restaurante.modelos.TokenType;
import com.example.api_restaurante.modelos.Usuario;
import com.example.api_restaurante.repositorios.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilidadesToken {
    private  final TokenRepository repositorioToken;
    private static final String EMISSOR = "BRUNO";
    private static final String CABECALHO = "Bearer ";
    private static final String CHAVE = "59703273357638792F423F4528482B4D6251655468576D5A7134743677397A24";
    private static final long SEGUNDO = 1000;
    private static final long MINUTO = 60 * SEGUNDO;

    private static final long HORA = 60 * MINUTO;

    public static <T> Token gerarToken(T objecto) {
        Key chaveSecreta = Keys.hmacShaKeyFor(CHAVE.getBytes());

        if(objecto instanceof Usuario usuario){
            return token(chaveSecreta, usuario.getEmail());
        }
        else{
            throw new IllegalArgumentException("Tipo inválido para o parâmetro objecto");
        }

    }

    private static Token token(Key chaveSecreta, String email) {
        String tokenJwt = Jwts.builder()
                .setSubject(email)
                .setIssuer(EMISSOR)
                .setExpiration(new Date(System.currentTimeMillis() + HORA))
                .signWith(chaveSecreta, SignatureAlgorithm.HS256)
                .compact();

        return Token.builder()
                .id(null)
                .token(tokenJwt)
                .revogado(false)
                .expiracao(false)
                .tokenType(TokenType.BEARER)
                .build();
    }



    public static String extrairToken(HttpServletRequest request){
        String tokenJwt = request.getHeader("Authorization");
        tokenJwt = tokenJwt.replace(CABECALHO,"");

        return tokenJwt;
    }

    public static String extrairEmail(HttpServletRequest request){
        String tokenJwt = request.getHeader("Authorization");
        tokenJwt = tokenJwt.replace(CABECALHO,"");

        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(CHAVE.getBytes()).build().parseClaimsJws(tokenJwt);

        return claimsJws.getBody().getSubject();
    }

    public static String extrairEmail(String cabecalho){

        String tokenJwt = cabecalho.replace(CABECALHO,"");

        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(CHAVE.getBytes()).build().parseClaimsJws(tokenJwt);

        return claimsJws.getBody().getSubject();
    }

    public  <T> Token salvarToken(T objecto, Token token) {
        if (objecto instanceof Usuario) {
            token.setUsuario((Usuario) objecto);
        }  else {
            throw new IllegalArgumentException("Tipo inválido para o parâmetro objecto");
        }

        return repositorioToken.save(token);
    }

    public  <T> void revogarTokens(T objeto) {
        if (objeto instanceof Usuario usuario) {
            List<Token> tokens = repositorioToken.findAllValidToken(usuario.getId());
            if (tokens.isEmpty())
                return;
            tokens.forEach(token -> {
                token.setExpiracao(true);
                token.setRevogado(true);
            });
            repositorioToken.saveAll(tokens);
        }  else {
            throw new IllegalArgumentException("Tipo inválido para o parâmetro objeto");
        }
    }









}
