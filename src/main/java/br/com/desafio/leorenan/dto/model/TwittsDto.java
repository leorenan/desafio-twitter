package br.com.desafio.leorenan.dto.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TwittsDto {

	private String id;
	private List<String> hashtagNome;
	private String mensagem;
	private String usuarioNome;
	private Integer usuarioQuantidadeSeguidores;
	private String jsonRetornoTwitter;
}
