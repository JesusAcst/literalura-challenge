package com.aluracursos.literalura_challenge.config;

import com.aluracursos.literalura_challenge.models.Autor;
import com.aluracursos.literalura_challenge.models.DatosLibro;
import com.aluracursos.literalura_challenge.models.Libro;
import com.aluracursos.literalura_challenge.models.LibrosRtaAPI;
import com.aluracursos.literalura_challenge.repository.IAutorRepository;
import com.aluracursos.literalura_challenge.repository.ILibroRepository;
import com.aluracursos.literalura_challenge.service.ConsumoAPI;
import com.aluracursos.literalura_challenge.service.ConvertirDatos;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Controller {
    private final ILibroRepository libroRepository;
    private final IAutorRepository autorRepository;
    private final ConsumoAPI consumoApi;
    private final ConvertirDatos convertir;
    private static String API_BASE = "https://gutendex.com/books/?search=";

    public Controller(ILibroRepository libroRepository, IAutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.consumoApi = new ConsumoAPI();
        this.convertir = new ConvertirDatos();
    }


    public void buscarLibroEnLaWeb(String nombreLibro) {
        try {
            String nombreLibroCodificado = URLEncoder.encode(nombreLibro, StandardCharsets.UTF_8.toString());
            String url = API_BASE + nombreLibroCodificado;
            String jsonResponse = consumoApi.obtenerDatos(url);
            LibrosRtaAPI librosRespuesta = convertir.convertirDatosJsonAJava(jsonResponse, LibrosRtaAPI.class);

            if (librosRespuesta != null && librosRespuesta.getResultadoLibros() != null) {
                for (DatosLibro datosLibro : librosRespuesta.getResultadoLibros()) {
                    boolean existe = libroRepository.existsByTitulo(datosLibro.titulo());
                    if (!existe) {
                        Libro libro = new Libro(datosLibro);
                        libroRepository.save(libro);
                        System.out.println("Libro guardado: " + datosLibro.titulo());
                    } else {
                        System.out.println("El libro ya existe en la base de datos: " + datosLibro.titulo());
                    }
                }
            } else {
                System.out.println("No se encontró el libro con el nombre especificado.");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al buscar el libro: " + e.getMessage());
        }
    }


    public void librosBuscados() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros buscados.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    public void buscarLibroPorNombre(String nombreLibro) {
        List<Libro> libros = libroRepository.findByTituloContainsIgnoreCase(nombreLibro);
        if (libros.isEmpty()) {
            System.out.println("Libro no encontrado.");
        } else {
            libros.forEach(System.out::println); // Imprime cada libro encontrado
        }
    }


    public void buscarAutores() {
        List<Autor> autores = autorRepository.findAllWithLibros();
        if (autores.isEmpty()) {
            System.out.println("No hay autores encontrados.");
        } else {
            autores.forEach(autor -> {
                System.out.println("\nNombre del autor: " + autor.getNombre()+"    Id:"+autor.getId());
                autor.getLibros().forEach(libro -> System.out.println(" - Libro: " + libro.getTitulo()));
            });
        }
    }


    public List<Autor> buscarAutoresPorAño() {
        String añoStr = InteraccionUsuario.pedirEntrada("Ingrese el año para buscar autores: ");
        int año = Integer.parseInt(añoStr);

        List<Autor> autores = autorRepository.findAll().stream()
                .filter(autor -> autor.getNacimiento() != null && autor.getNacimiento() == año)
                .collect(Collectors.toList());

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores nacidos en el año " + año);
        } else {
            System.out.println("Autores nacidos en el año " + año + ":");
            autores.forEach(autor -> System.out.println(" - " + autor.getNombre()));
        }
        return autores;
    }


    public List<Libro> buscarLibrosPorIdioma() {
        System.out.println("|  Opción - es : Libros en español. |");
        System.out.println("|  Opción - en : Libros en ingles.  |");
        String idioma = InteraccionUsuario.pedirEntrada("Ingrese el idioma para buscar libros: ");
        List<Libro> libros = libroRepository.findAll().stream()
                .filter(libro -> idioma.equalsIgnoreCase(libro.getIdioma()))
                .collect(Collectors.toList());
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma " + idioma);
        } else {
            System.out.println("Libros en el idioma " + idioma + ":");
            libros.forEach(libro -> System.out.println(" - " + libro.getTitulo()));
        }
        return libros;
    }

    public List<Libro> top10LibrosMasDescargados() {
        List<Libro> librosTop = libroRepository.findAll().stream()
                .sorted(Comparator.comparing(Libro::getNumeroDeDescargas, Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toList());
        if (librosTop.isEmpty()) {
            System.out.println("No hay libros con descargas registradas.");
        } else {
            System.out.println("Top 10 libros más descargados:");
            librosTop.forEach(libro ->
                    System.out.println(" - " + libro.getTitulo() + " (Descargas: " + libro.getNumeroDeDescargas() + ")"));
        }
        return librosTop;
    }

    public void buscarAutorPorNombre() {
        String escritor = InteraccionUsuario.pedirEntrada("Ingrese nombre del escritor que quiere buscar: ");
        Optional<Autor> escritorBuscado = autorRepository.findFirstByNombreContainsIgnoreCase(escritor);

        if (escritorBuscado.isPresent()) {
            System.out.println("\nEl escritor buscado fue: " + escritorBuscado.get().getNombre());
        } else {
            System.out.println("\nEl escritor con el nombre '" + escritor + "' no se encontró.");
        }
    }


}

