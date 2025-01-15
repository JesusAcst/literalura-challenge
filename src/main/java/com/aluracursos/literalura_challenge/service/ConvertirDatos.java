package com.aluracursos.literalura_challenge.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.aluracursos.literalura_challenge.service.IConfig.IConvertirDatos;

public class ConvertirDatos implements IConvertirDatos {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T convertirDatosJsonAJava(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
