package com.example.api_restaurante.repositorios;

import com.example.api_restaurante.modelos.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("""
    select t from Token t inner join Usuario i on t.usuario.id = i.id
    where i.id =:usuarioId and (t.expiracao = false or t.revogado = false)
    """)
    List<Token> findAllValidToken(Long usuarioId);

    Optional<Token> findByToken(String token);
}