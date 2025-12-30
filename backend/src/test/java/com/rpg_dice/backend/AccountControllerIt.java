package com.rpg_dice.backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpg_dice.backend.infra.dto.AccountRequestDto;

@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
class AccountControllerIt {
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
		AccountRequestDto dto = new AccountRequestDto(
				"nome1",
				"email1@gmail.com",
				"@passwor1"
		);
		
	    String object =  objectMapper.writeValueAsString(dto);
	    
	    mockMvc.perform(post("/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(print());
	}
}
