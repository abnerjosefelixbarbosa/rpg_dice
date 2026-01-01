package com.rpg_dice.backend.infra.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ExceptionDetailsDTO(
		@JsonFormat(pattern = "yyyy-MM-dd HH:ss")
		LocalDateTime localDateTime,
		Integer status,
		String message,
		String path
) {}