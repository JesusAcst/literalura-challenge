package com.aluracursos.literalura_challenge.config;

import java.util.Scanner;

public class InteraccionUsuario {

    private static final Scanner scanner = new Scanner(System.in);

    public static String pedirEntrada(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
}
