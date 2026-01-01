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
import com.rpg_dice.backend.infra.repository.AccountEntityRepository;
import com.rpg_dice.backend.infra.repository.PlayerEntityRepository;

@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
public class AccountPlayerEmailControllerTI {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private AccountEntityRepository accountEntityRepository;
	@Autowired
	private PlayerEntityRepository playerEntityRepository;

	@BeforeEach
	void setUp() throws Exception {
		accountEntityRepository.deleteAll();
		playerEntityRepository.deleteAll();
	}

	@AfterEach
	void tearDown() throws Exception {
		accountEntityRepository.deleteAll();
		playerEntityRepository.deleteAll();
	}
	
	@Test
	void shouldCreateWithNullPlayerEmailAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO("@Passwor1", "nome1", null);

		String object = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.playerEmail").value("Email do jogador não deve ser nulo ou vázio."))
				.andDo(print());
	}

	@Test
	void shouldCreateWithEmptyPlayerEmailAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO("@Passwor1", "nome1", "");

		String object = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.playerEmail").value("Email do jogador não deve ser nulo ou vázio."))
				.andDo(print());
	}

	@Test
	void shouldCreateWithInvalidPlayerEmailAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO("@Password1", "nome1", "email1");

		String object = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.playerEmail").value("Email do jogador deve ser valido.")).andDo(print());
	}
	
	@Test
	void shouldCreateWithMoreThan50CharactersPlayerEmailAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO("@Password1", "nome1", "email11111111111111111111111111111111111111111111111111111111111@gmail.com");

		String object = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.playerEmail").value("Email do jogador não deve ter mais de 50 caracteres.")).andDo(print());
	}
}