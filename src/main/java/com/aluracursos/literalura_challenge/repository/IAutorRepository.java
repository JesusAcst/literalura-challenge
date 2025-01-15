package com.aluracursos.literalura_challenge.repository;

import com.aluracursos.literalura_challenge.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IAutorRepository extends JpaRepository<Autor,Long> {
    @Query("SELECT a FROM Autor a JOIN FETCH a.libros")
    List<Autor> findAllWithLibros();
    List<Autor> findByNacimientoLessThanOrFallecimientoGreaterThanEqual(int anioBuscado, int anioBuscado1);
    Optional<Autor> findFirstByNombreContainsIgnoreCase(String escritor);
}
