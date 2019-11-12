package br.com.desafio.leorenan.service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

import br.com.desafio.leorenan.service.model.Root;
import br.com.desafio.leorenan.util.PropertiesUtil;

public class TwitterBuscaHashtag {

	public static Root buscar(List<String> hashtags) {
		String q = formataQueryParameters(hashtags);
		
		String uri = PropertiesUtil.getTwitterBaseUrl().concat("/1.1/search/tweets.json");
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", "Bearer " + TwitterOAuthService.getToken());
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(uri)
		        .queryParam("q", q)
		        .queryParam("include_entities", "true")
		        .encode(StandardCharsets.UTF_8)
                .build();
		
		ResponseEntity<String> res = rt.exchange(builder.toUri(), HttpMethod.GET, entity, String.class);
	
		Gson gson = new Gson();
		return gson.fromJson( res.getBody(), Root.class ); 
	}

	private static String formataQueryParameters(List<String> hashtags) {
		StringBuilder sb = new StringBuilder();

		Iterator<String> iteratorHashtags = hashtags.iterator();

		while (iteratorHashtags.hasNext()) {
			String hashtag = iteratorHashtags.next();
			if (!hashtag.startsWith("#")) {
				hashtag = "#" + hashtag;
			}

			sb.append(hashtag);

			if (iteratorHashtags.hasNext()) {
				sb.append("+");
			}

		}

		return sb.toString();
	}

}
