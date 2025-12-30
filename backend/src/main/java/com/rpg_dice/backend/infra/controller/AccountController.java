package com.rpg_dice.backend.infra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rpg_dice.backend.application.usercase.AccountUsercase;
import com.rpg_dice.backend.infra.dto.AccountRequestDto;
import com.rpg_dice.backend.infra.dto.AccountResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/accounts")
public class AccountController {
	private final AccountUsercase accountUsercase;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(value = "/create")
	public ResponseEntity<AccountResponseDto> create(@Valid @RequestBody AccountRequestDto dto) {
		accountUsercase.create(null);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
}