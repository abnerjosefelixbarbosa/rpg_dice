package com.rpg_dice.backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import com.rpg_dice.backend.infra.entity.AccountEntity;
import com.rpg_dice.backend.infra.entity.PlayerEntity;
import com.rpg_dice.backend.infra.repository.AccountEntityRepository;
import com.rpg_dice.backend.infra.repository.PlayerEntityRepository;

@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
class AccountControllerTI {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private AccountEntityRepository accountEntityRepository;
	@Autowired
	private PlayerEntityRepository playerEntityRepository;
	private PlayerEntity playerEntity;
	private AccountEntity accountEntity;

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
	void shouldCreateAndReturnStatus201() throws Exception {
		AccountRequestDTO dto = new AccountRequestDTO("@Passwor1", "nome1", "email1@gmail.com");

		String object = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isCreated()).andDo(print());
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
				"Nome1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111",
				"email1@gmail.com");

		String object = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.playerName").value("Nome do jogador não deve ter mais de 100 caracteres."))
				.andDo(print());
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
		AccountRequestDTO dto = new AccountRequestDTO("@Password1", "nome1",
				"email11111111111111111111111111111111111111111111111111111111111@gmail.com");

		String object = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.playerEmail").value("Email do jogador não deve ter mais de 50 caracteres."))
				.andDo(print());
	}

	@Test
	void shouldCreateWithExistsPasswordOrPlayerNameOrPlayerEmailAndReturnStatus400() throws Exception {
		loadPlayer();

		loadAccount();

		AccountRequestDTO dto = new AccountRequestDTO("@Password1", "nome1", "email1@gmail.com");

		String object = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message")
						.value("Senha, nome do jogador ou email do jogador não deve ser repetido."))
				.andDo(print());
	}

	void loadPlayer() {
		List<PlayerEntity> playerEntities = new ArrayList<PlayerEntity>();

		playerEntities.add(new PlayerEntity(UUID.randomUUID().toString(), "nome1", "email1@gmail.com", null));

		playerEntityRepository.saveAll(playerEntities);

		playerEntity = playerEntities.get(0);
	}

	void loadAccount() {
		List<AccountEntity> accountEntities = new ArrayList<AccountEntity>();

		accountEntities.add(new AccountEntity(UUID.randomUUID().toString(), "@Password1", playerEntity, null));

		accountEntityRepository.saveAll(accountEntities);

		accountEntity = accountEntities.get(0);
	}
}
