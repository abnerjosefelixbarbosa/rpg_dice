package com.rpg_dice.backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
	//private PlayerEntity playerEntity;
	//private AccountEntity accountEntity;

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
		load();
		
		AccountRequestDTO dto = new AccountRequestDTO("@Passwor1", "nome1", "email1@gmail.com");

		String object = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isCreated()).andDo(print());
	}
	
	void load() {
		List<PlayerEntity> playerEntities = new ArrayList<PlayerEntity>();
		
		playerEntities.add(new PlayerEntity(UUID.randomUUID().toString(), "nome1", "email1@gmail.com", null));
		
		playerEntityRepository.saveAll(playerEntities);
		
		List<AccountEntity> accountEntities = new ArrayList<AccountEntity>();
		
		accountEntities.add(new AccountEntity(UUID.randomUUID().toString(), "@Password1", playerEntities.get(0), null));
		
		accountEntityRepository.saveAll(accountEntities);
		
		//accountEntity = accountEntities.get(0);
	}
}
