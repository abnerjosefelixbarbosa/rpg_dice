package com.rpg_dice.backend.application.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Status {
	private Long strength;
	private Long dexterity;
	private Long constitution;
	private Long intelligence;
	private Long charisma;
	private Long wisdom;
}