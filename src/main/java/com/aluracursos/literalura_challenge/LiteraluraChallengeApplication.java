package com.aluracursos.literalura_challenge;
import com.aluracursos.literalura_challenge.Principal.Principal;
import com.aluracursos.literalura_challenge.repository.IAutorRepository;
import com.aluracursos.literalura_challenge.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraChallengeApplication implements CommandLineRunner {

	@Autowired
	private ILibroRepository libroRepository;
	@Autowired
	private IAutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal libreria = new Principal(libroRepository, autorRepository);
		libreria.menu();

	}
}
