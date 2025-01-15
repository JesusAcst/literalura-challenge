package com.aluracursos.literalura_challenge.Principal;
import com.aluracursos.literalura_challenge.config.AppConfig;
import com.aluracursos.literalura_challenge.config.Controller;
import com.aluracursos.literalura_challenge.repository.IAutorRepository;
import com.aluracursos.literalura_challenge.repository.ILibroRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.*;

public class Principal {

    private final Controller controller;
    private final Scanner scanner;

    public Principal(ILibroRepository libroRepository, IAutorRepository autorRepository) {
        this.controller = new Controller(libroRepository, autorRepository);
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \n
                    |//////////    BIENVENIDO A LITERALURA    //////////|
                    1 - Agregar Libro por Nombre
                    2 - Libros buscados
                    3 - Buscar libro por Nombre
                    4 - Buscar todos los Autores de libros buscados
                    5 - Buscar Autores por año
                    6 - Buscar Libros por Idioma
                    7 - Top 10 Libros mas Descargados
                    8 - Buscar Autor por Nombre
                    0 - Salir
                    |/////////////   INGRESE UNA OPCIÓN   /////////////|
                    """;
            try {
                System.out.println(menu);
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("|--------  Ingrese un número válido.  --------|");
                scanner.nextLine();
                continue;
            }
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del libro: ");
                    String nombreLibro = scanner.nextLine();
                    controller.buscarLibroEnLaWeb(nombreLibro);
                    break;
                case 2:
                    controller.librosBuscados();
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del libro a buscar: ");
                    String nombreBuscar = scanner.nextLine();
                    controller.buscarLibroPorNombre(nombreBuscar);
                    break;
                case 4:
                    controller.buscarAutores();
                    break;
                case 5:
                    controller.buscarAutoresPorAño();
                    break;
                case 6:
                    controller.buscarLibrosPorIdioma();
                    break;
                case 7:
                    controller.top10LibrosMasDescargados();
                    break;
                case 8:
                    controller.buscarAutorPorNombre() ;
                    break;
                case 0:
                    opcion = 0;
                    System.out.println("|--------  Programa finalizado  --------|");
                    break;
                default:
                    System.out.println("|--------  Opción Incorrecta. --------|");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Principal principal = context.getBean(Principal.class);
        principal.menu();
    }
}
