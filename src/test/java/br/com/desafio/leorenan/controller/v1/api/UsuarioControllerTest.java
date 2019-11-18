package br.com.desafio.leorenan.controller.v1.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.desafio.leorenan.dto.response.Response;
import br.com.desafio.leorenan.dto.response.Response.Status;
import br.com.desafio.leorenan.repository.TwittsRepository;
import br.com.desafio.leorenan.service.TwittsServiceImpl;

@SpringBootTest
public class UsuarioControllerTest {

	@Autowired
	UsuarioController controller;
	
	@Mock
    private TwittsRepository repository;
	
	@InjectMocks
	TwittsServiceImpl serviceImpl = new TwittsServiceImpl();
	
	 @DisplayName("Retorna o TOP 5 de usuarios com mais seguidores")
	    @Test
	    void testGet() {
		 	@SuppressWarnings("rawtypes")
			Response response = controller.getAll();
		 	assertEquals(Status.OK, response.getStatus());
	    }
	
}
