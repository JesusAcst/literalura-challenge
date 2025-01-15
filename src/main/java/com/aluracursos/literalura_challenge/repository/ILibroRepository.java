package com.aluracursos.literalura_challenge.repository;
import com.aluracursos.literalura_challenge.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByTitulo(String titulo);
    List<Libro> findByTituloContainsIgnoreCase(String titulo);
    List<Libro> findByIdioma(String idioma);
    @Query("SELECT l FROM Libro l ORDER BY l.numeroDeDescargas DESC LIMIT 10")
    List<Libro> findTop10ByTituloByCantidadDescargas();
}
