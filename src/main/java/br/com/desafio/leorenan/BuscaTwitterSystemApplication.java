package br.com.desafio.leorenan;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.desafio.leorenan.repository.TwittsRepository;

@SpringBootApplication
public class BuscaTwitterSystemApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BuscaTwitterSystemApplication.class, args);		
	}
	
	@Bean
    CommandLineRunner init(TwittsRepository hashtagRepository) {
		return args -> {

		};
		
	}
	
	
}
