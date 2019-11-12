package br.com.desafio.leorenan.service;

import java.util.List;

import br.com.desafio.leorenan.dto.model.TwittsDto;

public interface  TwittsService {

	List<TwittsDto> addHashtag(List<String> hashtagsName);
}
