package com.aluracursos.literalura_challenge.models;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Media(
        @JsonAlias("image/jpeg") String imagen
){}
