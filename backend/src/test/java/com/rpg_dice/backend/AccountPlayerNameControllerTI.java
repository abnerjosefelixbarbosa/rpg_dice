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
public class AccountPlayerNameControllerTI {
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
	void shouldCreateWithNullPlayerNameAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO("@Passwor1", null, "email1@gmail.com");

		String object = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.playerName").value("Nome do jogador não deve ser nulo ou vázio."))
				.andDo(print());
	}

	@Test
	void shouldCreateWithBlankPlayerNameAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO("@Passwor1", "", "email1@gmail.com");

		String object = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.playerName").value("Nome do jogador não deve ser nulo ou vázio."))
				.andDo(print());
	}

	@Test
	void shouldCreateWithMoreThan100CharactersPlayerNameAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO("@Passwor1",
				"Nome1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111",
				"email1@gmail.com");

		String object = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.playerName").value("Nome do jogador não deve ter mais de 100 caracteres."))
				.andDo(print());
	}
}