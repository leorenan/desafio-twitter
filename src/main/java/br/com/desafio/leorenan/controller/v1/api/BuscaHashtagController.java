package br.com.desafio.leorenan.controller.v1.api;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.leorenan.dto.response.Response;
import br.com.desafio.leorenan.service.TwittsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/twitts/")
@Api(value = "twitts hashtag")
public class BuscaHashtagController {

	@Autowired
	private TwittsServiceImpl twittsServiceImpl;

	@SuppressWarnings("rawtypes")
	@GetMapping("/hashtag")
	@ApiOperation(value = "Busca todas a hashtag", httpMethod = "GET", notes = "Busca os 100 Twitts com a determida Hashtags ")
	@ApiResponses(value = {
			 @ApiResponse(code=200, message = "Retorna a lista que foi pesquisada no Twitter"),
			 @ApiResponse(code=400, message = "Retorna o erro quando a lista de hashtags esta sem elementos")
	})
	public Response getAll(
			@ApiParam(value = "Lista de hashtags passadas via query parameters", required = true) @RequestParam(required = true) List<String> hashtags) {
		
		if(hashtags == null || hashtags.size() < 1) {
			InvalidParameterException e = new InvalidParameterException("Informe pelo menos uma hashtag");
			return Response.validationException().setErrors(e);
		}
		
		return Response.ok().setPayload(twittsServiceImpl.addHashtag(hashtags));
	}

}
