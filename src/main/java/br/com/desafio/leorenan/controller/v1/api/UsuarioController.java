package br.com.desafio.leorenan.controller.v1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.leorenan.dto.response.Response;
import br.com.desafio.leorenan.service.TwittsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/usuarios/")
@Api(value = "Usuarios")
public class UsuarioController {

	@Autowired
	private TwittsServiceImpl twittsServiceImpl;

	@GetMapping("/top-5-seguidores")
	@ApiOperation(value = "Busca usuarios com mais seguidores", httpMethod = "GET", notes = "Busca pelos 5 usuarios que mais tem seguidores")
	@ApiResponses(value = {
			 @ApiResponse(code=200, message = "Retorna a lista dos 5 usuarios")
	})
	public Response getAll() {
		
		return Response.ok().setPayload(twittsServiceImpl.getTop5UsuarioSeguidores());
	}

}
