package br.com.desafio.leorenan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.com.desafio.leorenan.dto.model.TwittsDto;
import br.com.desafio.leorenan.model.Twitts;
import br.com.desafio.leorenan.repository.TwittsRepository;
import br.com.desafio.leorenan.service.model.Hashtags;
import br.com.desafio.leorenan.service.model.Root;
import br.com.desafio.leorenan.service.model.Statuses;
import br.com.desafio.leorenan.util.ObjectMapperUtils;

@Component
public class TwittsServiceImpl implements TwittsService {

	@Autowired
	TwittsRepository twittsRepository;

	@Override
	public List<TwittsDto> addHashtag(List<String> hashtagsName) {
		Root root = TwitterBuscaHashtag.buscar(hashtagsName);
		List<Twitts> twittsList = new ArrayList<>();

		for (Statuses s : root.getStatuses()) {
			int hashtagCount = 0;

			List<String> hashtagNames = new ArrayList<>();

			for (Hashtags h : s.getEntities().getHashtags()) {	
				if (hashtagsName.stream().anyMatch(str -> str.toLowerCase().trim().contains(h.getText().toLowerCase().trim())))
					hashtagCount++;

				hashtagNames.add(h.getText());
			}

			if (hashtagCount > 0) {
				Gson gson = new Gson();
				String jsonRetornoTwitter = gson.toJson(s);

				Twitts twitts = new Twitts();
				twitts.setHashtagNome(hashtagNames);
				twitts.setUsuarioNome(s.getUser().getName());
				twitts.setUsuarioQuantidadeSeguidores(s.getUser().getFollowers_count());
				twitts.setMensagem(s.getText());
				twitts.setJsonRetornoTwitter(jsonRetornoTwitter);

				twittsList.add(twitts);
			}
		}

		if (twittsRepository != null)
			twittsRepository.saveAll(twittsList);
		return ObjectMapperUtils.mapAll(twittsList, TwittsDto.class);
	}
	
	public List<TwittsDto> getTop5UsuarioSeguidores(){
		List<Twitts> twittsList = twittsRepository.findTop5UsuarioNomeDistinctByOrderByUsuarioQuantidadeSeguidoresDesc();
				
		return ObjectMapperUtils.mapAll(twittsList, TwittsDto.class);
	}

}
