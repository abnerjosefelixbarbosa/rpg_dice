package com.rpg_dice.backend.infra.dto;

public record AccountRequestDto(
		String playerName,
		String playerEmail,
		String password
) {}