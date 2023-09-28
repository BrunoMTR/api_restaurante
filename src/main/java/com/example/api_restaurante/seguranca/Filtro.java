package com.example.api_restaurante.seguranca;

import com.example.api_restaurante.modelos.Token;
import com.example.api_restaurante.repositorios.TokenRepository;
import com.example.api_restaurante.servicos.ServicoDetais;
import com.example.api_restaurante.servicos.UtilidadesToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import java.io.IOException;
@Component
@Slf4j
@RequiredArgsConstructor
public class Filtro extends OncePerRequestFilter {
    private final ServicoDetais servicoDetais;
    private final TokenRepository repositorioToken;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //caso seja de registro ou login deixe passar e será lidado pelo serviso isfl e servico doador
        if (request.getServletPath().contains("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String cabecalho = request.getHeader("Authorization");
        final String jwt;
        final String email;
        //se o cabeçalho for null ou não começar com bear, encaminhe pro proximo filtro e retorne
        if (cabecalho == null ||!cabecalho.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        email = UtilidadesToken.extrairEmail(request);

        //se tiver um email e não estiver já logado, faça isso:
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.servicoDetais.loadUserByUsername(email);

            jwt = UtilidadesToken.extrairToken(request);
            Token token = repositorioToken.findByToken(jwt).orElseThrow();


            if(!token.isExpiracao() && !token.isRevogado()){
                System.out.println(userDetails.getAuthorities());
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);


    }
}