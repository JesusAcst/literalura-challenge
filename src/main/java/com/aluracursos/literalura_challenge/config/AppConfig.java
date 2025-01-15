package com.aluracursos.literalura_challenge.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.aluracursos.literalura_challenge"})
@EnableJpaRepositories(basePackages = {"com.aluracursos.literalura_challenge.repository"})
@EntityScan(basePackages = {"com.aluracursos.literalura_challenge.models"})
public class AppConfig {
}

