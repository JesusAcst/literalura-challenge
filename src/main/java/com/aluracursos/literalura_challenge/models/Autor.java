package com.aluracursos.literalura_challenge.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer nacimiento;

    private Integer fallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Libro> libros;


    public Autor() {
    }



    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public Integer getFallecimiento() {
        return fallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Autor(com.aluracursos.literalura_challenge.models.Autor autor) {
        this.id = autor.id;
        this.nombre = autor.nombre;
        this.nacimiento = autor.nacimiento;
        this.fallecimiento = autor.fallecimiento;
    }


    @Override
    public String toString() {
        return
                "id:"+id+
                ", nombre:'" + nombre + '\'' +
                ", Fecha de nacimiento:" + nacimiento +
                ", Fecha de fallecimiento:" + fallecimiento;
    }
}
