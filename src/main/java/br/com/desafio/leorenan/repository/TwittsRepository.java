package br.com.desafio.leorenan.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.desafio.leorenan.model.Twitts;

public interface TwittsRepository extends MongoRepository<Twitts, String> {

	Twitts findByHashtagNome(String hashtagNome);
	
	List<Twitts> findTop5UsuarioNomeDistinctByOrderByUsuarioQuantidadeSeguidoresDesc();
}
