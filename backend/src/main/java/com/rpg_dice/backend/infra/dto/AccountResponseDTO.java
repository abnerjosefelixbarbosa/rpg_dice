package com.rpg_dice.backend.infra.dto;

public record AccountResponseDTO(
		String playerName,
		String playerEmail,
		String password
) {}