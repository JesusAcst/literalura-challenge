package com.aluracursos.literalura_challenge.service.IConfig;

public interface IConvertirDatos {

    <T> T convertirDatosJsonAJava(String json , Class<T> clase);
}
