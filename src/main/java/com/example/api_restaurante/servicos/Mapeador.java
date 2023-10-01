package com.example.api_restaurante.servicos;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mapeador {
    private final ModelMapper modelMapper;

    @Autowired
    public Mapeador(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public  <C,D>D converterParaDTO(Class<D>dto, C classe) {
        return modelMapper.map(classe, dto);
    }

    public <C, D> C converterParaClasse(Class<C> classe, D dto) {
        return modelMapper.map(dto, classe);
    }

    public <C, D> List<D> converterListaParaDTO(Class<D> dtoClass, List<C> entityList) {
        return entityList.stream()
                .map(entity -> modelMapper.map(entity, dtoClass))
                .collect(Collectors.toList());
    }
}
