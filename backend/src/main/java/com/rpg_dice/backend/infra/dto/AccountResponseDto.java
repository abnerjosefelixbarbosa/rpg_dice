package com.rpg_dice.backend.infra.dto;

public record AccountResponseDto(
		String playerName,
		String playerEmail,
		String password
) {}