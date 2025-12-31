package com.rpg_dice.backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpg_dice.backend.infra.dto.AccountRequestDTO;

@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
class AccountControllerTI {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void shouldCreateAndReturnStatus201() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO(
				"nome1",
				"email1@gmail.com",
				"@Passwor1"
		);
		
	    String object =  objectMapper.writeValueAsString(dto);
	    
	    mockMvc.perform(
	    		post("/accounts/create")
	    		.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(object)
		)
	    .andExpect(status().isCreated())
	    .andDo(print());
	}
	
	@Test
	void shouldCreateWithNullNameAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO(
				null,
				"email1@gmail.com",
				"@passwor1"
		);
		
        String object =  objectMapper.writeValueAsString(dto);
	    
	    mockMvc.perform(
	    		post("/accounts/create")
	    		.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(object)
		)
	    .andExpect(status().isBadRequest())
	    .andExpect(jsonPath("$.playerName").value("Nome do jogador não deve ser nulo ou vázio."))
	    .andDo(print());
	}
	
	@Test
	void shouldCreateWithBlankNameAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO(
				"",
				"email1@gmail.com",
				"@passwor1"
		);
		
        String object =  objectMapper.writeValueAsString(dto);
	    
	    mockMvc.perform(
	    		post("/accounts/create")
	    		.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(object)
		)
	    .andExpect(status().isBadRequest())
	    .andExpect(jsonPath("$.playerName").value("Nome do jogador não deve ser nulo ou vázio."))
	    .andDo(print());
	}
	
	@Test
	void shouldCreateWithNullEmailAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO(
				"nome1",
				null,
				"@passwor1"
		);
		
        String object =  objectMapper.writeValueAsString(dto);
	    
	    mockMvc.perform(
	    		post("/accounts/create")
	    		.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(object)
		)
	    .andExpect(status().isBadRequest())
	    .andExpect(jsonPath("$.playerEmail").value("Email do jogador não deve ser nulo ou vázio."))
	    .andDo(print());
	}
	
	@Test
	void shouldCreateWithEmptyEmailAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO(
				"nome1",
				"",
				"@passwor1"
		);
		
        String object =  objectMapper.writeValueAsString(dto);
	    
	    mockMvc.perform(
	    		post("/accounts/create")
	    		.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(object)
		)
	    .andExpect(status().isBadRequest())
	    .andExpect(jsonPath("$.playerEmail").value("Email do jogador não deve ser nulo ou vázio."))
	    .andDo(print());
	}
	
	@Test
	void shouldCreateWithInvalidEmailAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO(
				"nome1",
				"email1",
				"@passwor1"
		);
		
        String object =  objectMapper.writeValueAsString(dto);
	    
	    mockMvc.perform(
	    		post("/accounts/create")
	    		.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(object)
		)
	    .andExpect(status().isBadRequest())
	    .andExpect(jsonPath("$.playerEmail").value("Email do jogador deve ser valido."))
	    .andDo(print());
	}
	
	@Test
	void shouldCreateWithNullPasswordAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO(
				"nome1",
				"email1@gmail.com",
				null
		);
		
        String object =  objectMapper.writeValueAsString(dto);
	    
	    mockMvc.perform(
	    		post("/accounts/create")
	    		.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(object)
		)
	    .andExpect(status().isBadRequest())
	    .andExpect(jsonPath("$.password").value("Senha não deve ser nulo ou vázio."))
	    .andDo(print());
	}
	
	@Test
	void shouldCreateWithEmptyPasswordAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO(
				"nome1",
				"email1@gmail.com",
				""
		);
		
        String object =  objectMapper.writeValueAsString(dto);
	    
	    mockMvc.perform(
	    		post("/accounts/create")
	    		.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(object)
		)
	    .andExpect(status().isBadRequest())
	    .andExpect(jsonPath("$.password").value("Senha não deve ser nulo ou vázio."))
	    .andDo(print());
	}
	
	@Test
	void shouldCreateWithInvalidPasswordAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO(
				"nome1",
				"email1@gmail.com",
				"password"
		);
		
        String object =  objectMapper.writeValueAsString(dto);
	    
	    mockMvc.perform(
	    		post("/accounts/create")
	    		.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(object)
		)
	    .andExpect(status().isBadRequest())
	    .andExpect(jsonPath("$.password").value("Senha deve ter caracteres especiais, caracteres numericos e com letra maiuscula."))
	    .andDo(print());
	}
}
