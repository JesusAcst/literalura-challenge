package com.aluracursos.literalura_challenge.models;

import com.aluracursos.literalura_challenge.dtos.Genero;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long libroId;

    @Column(unique = true)
    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private String idioma;
    private String imagen;
    private Long numeroDeDescargas;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.libroId = datosLibro.libroId();
        this.titulo = datosLibro.titulo();
        this.autor = new Autor();
        this.genero = generoModificado(datosLibro.genero());
        this.idioma = idiomaModificado(datosLibro.idioma());
        this.imagen = imagenModificada(datosLibro.imagen());
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    public Libro(Libro libro) {
    }

    private Genero generoModificado(List<String> generos) {
        if (generos == null || generos.isEmpty()) {
            return Genero.DESCONOCIDO;
        }
        Optional<String> firstGenero = generos.stream()
                .map(g -> {
                    int index = g.indexOf("--");
                    return index != -1 ? g.substring(index + 2).trim() : null;
                })
                .filter(Objects::nonNull)
                .findFirst();
        return firstGenero.map(Genero::fromString).orElse(Genero.DESCONOCIDO);
    }

    private String idiomaModificado(List<String> idiomas) {
        if (idiomas == null || idiomas.isEmpty()) {
            return "Desconocido";
        }
        return idiomas.get(0);
    }

    private String imagenModificada(Media media) {
        if (media == null || media.imagen().isEmpty()) {
            return "Sin imagen";
        }
        return media.imagen();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutores() {
        return autor;
    }

    public void setAutores(Autor autores) {
        this.autor = autores;
    }


    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }



    public Long getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Long numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return
                "  \nid=" + id +
                        "  \nLibro id = " + libroId +
                        ", \ntitulo = '" + titulo + '\'' +
                        ", \nauthors= " + autor+
                        ", \ngenero = " + genero +
                        ", \nidioma = " + idioma +
                        ", \nimagen = " + imagen +
                        ", \nNumero de " +
                        "Descargas = " + numeroDeDescargas;
    }
}
