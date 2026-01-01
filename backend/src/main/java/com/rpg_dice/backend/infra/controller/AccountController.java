package com.rpg_dice.backend.infra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rpg_dice.backend.application.entity.Account;
import com.rpg_dice.backend.application.usercase.AccountUsercase;
import com.rpg_dice.backend.infra.dto.AccountRequestDTO;
import com.rpg_dice.backend.infra.dto.AccountResponseDTO;
import com.rpg_dice.backend.infra.mapper.AccountMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/accounts")
public class AccountController {
	private final AccountUsercase accountUsercase;
	private final AccountMapper accountMapper;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(value = "/create")
	public ResponseEntity<AccountResponseDTO> create(@Valid @RequestBody AccountRequestDTO dto) {
		Account account = accountMapper.toAccount(dto);
		
		account = accountUsercase.create(account);
		
		AccountResponseDTO accountResponseDTO = accountMapper.toAccountResponseDTO(account);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(accountResponseDTO);
	}
}