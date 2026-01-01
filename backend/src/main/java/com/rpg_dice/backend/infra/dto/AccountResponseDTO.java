package com.rpg_dice.backend.infra.dto;

public record AccountResponseDTO(
		String id,
		String password,
		String playerId,
		String playerName,
		String playerEmail
) {}