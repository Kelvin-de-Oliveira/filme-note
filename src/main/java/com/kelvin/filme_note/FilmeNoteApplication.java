package com.kelvin.filme_note;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FilmeNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmeNoteApplication.class, args);
	}

}
