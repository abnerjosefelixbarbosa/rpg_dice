package com.rpg_dice.backend.infra.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AccountRequestDTO(
		@NotEmpty(message = "Senha não deve ser nulo ou vázio.")
		@NotNull(message = "Senha não deve ser nulo ou vázio.")
		@Length(max = 20, message = "Senha não deve ter mais de 20 caracteres.")
		@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_\\-+=\\[{\\]};:'\",.<>/?\\\\|]).+$", message = "Senha deve ter caracteres especiais, caracteres numericos e com letra maiuscula.")
		String password,
		@NotBlank(message = "Nome do jogador não deve ser nulo ou vázio.")
		@NotNull(message = "Nome do jogador não deve ser nulo ou vázio.")
		@Length(max = 100, message = "Nome do jogador não deve ter mais de 100 caracteres.")
		String playerName,
		@NotEmpty(message = "Email do jogador não deve ser nulo ou vázio.")
		@NotNull(message = "Email do jogador não deve ser nulo ou vázio.")
		@Email(message = "Email do jogador deve ser valido.")
		@Length(max = 50, message = "Email do jogador não deve ter mais de 50 caracteres.")
		String playerEmail
) {}