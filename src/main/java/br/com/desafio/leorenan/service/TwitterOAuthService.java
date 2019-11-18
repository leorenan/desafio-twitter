package br.com.desafio.leorenan.service;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import br.com.desafio.leorenan.service.model.Token;
import br.com.desafio.leorenan.util.PropertiesUtil;

public class TwitterOAuthService {

	private static String token; 
	
	
	
	private TwitterOAuthService() {
		
	}
	
	
	public static String getToken() {
		if(token == null || token.trim().isEmpty()) {
			String uri = PropertiesUtil.getTwitterBaseUrl().concat("/oauth2/token");
			RestTemplate rt = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setBasicAuth(PropertiesUtil.getTwitterConsumerKey(), PropertiesUtil.getTwitterConsumerSecret());
			
			MultiValueMap<String, String> req= new LinkedMultiValueMap<>();
			req.add("grant_type", "client_credentials");
			
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(req, headers);
	
			ResponseEntity<String> res = rt.exchange(uri, HttpMethod.POST, entity, String.class);
			
			Gson gson = new Gson();
			Token tokenObj = gson.fromJson( res.getBody(), Token.class ); 
			
			token = tokenObj.getAccess_token();			
		}
		
		return token;
	}
}
