package br.com.desafio.leorenan.controller.v1.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

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
public class BuscaHashtagControllerTest {

	@Autowired
	BuscaHashtagController controller;
	
	@Mock
    private TwittsRepository repository;
	
	@InjectMocks
	TwittsServiceImpl serviceImpl = new TwittsServiceImpl();
	
	 @DisplayName("Teste de buscar as hashtags selecionadas")
	    @Test
	    void testGetSucesso() {
		 	List<String> hashtags = new ArrayList<String>();
		 	hashtags.add("aws");
		 	@SuppressWarnings("rawtypes")
			Response response = controller.getAll(hashtags);
		 	assertEquals(Status.OK, response.getStatus());
	    }
	 
	 @DisplayName("Teste de buscar as hashtags selecionadas, parametro vazio")
	    @Test
	    void testGetErroParametroVazio() {
		 	List<String> hashtags = new ArrayList<String>();
		 	@SuppressWarnings("rawtypes")
			Response response = controller.getAll(hashtags);
		 	assertEquals(Status.VALIDATION_EXCEPTION, response.getStatus());
	    }
	 
	
	
}
