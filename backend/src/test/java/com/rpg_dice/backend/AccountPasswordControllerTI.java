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
public class AccountPasswordControllerTI {
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
	void shouldCreateWithNullPasswordAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO(null, "nome1", "email1@gmail.com");

		String object = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.password").value("Senha não deve ser nulo ou vázio.")).andDo(print());
	}

	@Test
	void shouldCreateWithEmptyPasswordAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO("", "nome1", "email1@gmail.com");

		String object = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.password").value("Senha não deve ser nulo ou vázio.")).andDo(print());
	}

	@Test
	void shouldCreateWithInvalidPasswordAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO("password", "nome1", "email1@gmail.com");

		String object = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/accounts/create")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(object))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.password")
						.value("Senha deve ter caracteres especiais, caracteres numericos e com letra maiuscula."))
				.andDo(print());
	}

	@Test
	void shouldCreateWithMoreThan20CharactersPasswordAndReturnStatus400() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO("@Password111111111111", "nome1", "email1@gmail.com");

		String object = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.password").value("Senha não deve ter mais de 20 caracteres.")).andDo(print());
	}
}