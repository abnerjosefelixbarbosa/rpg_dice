package com.rpg_dice.backend.application.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {
	private String id;
	private String description;
	private Long quantity;
	private Sheet sheet;
}