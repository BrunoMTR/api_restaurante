package com.example.api_restaurante.configuracoes;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracaoMapeador {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}
