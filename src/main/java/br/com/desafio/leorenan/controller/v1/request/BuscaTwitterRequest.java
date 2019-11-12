package br.com.desafio.leorenan.controller.v1.request;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuscaTwitterRequest {
	@NotEmpty(message = "{constraints.NotEmpty.message}")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	String hashtag;
	
	
}
