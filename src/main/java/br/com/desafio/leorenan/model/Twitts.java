package br.com.desafio.leorenan.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection="twitts")
public class Twitts {

	@Id
	private String id;
	
	@Indexed(direction = IndexDirection.ASCENDING)
	private List<String> hashtagNome;
	
	private String mensagem;
	
	private String usuarioNome;
	
	@Indexed(direction = IndexDirection.DESCENDING)
	private Integer usuarioQuantidadeSeguidores;
	
	private String jsonRetornoTwitter;
}
