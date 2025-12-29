package com.rpg_dice.backend.application.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
	private String id;
	private String password;
	private Player player;
	private List<Sheet> sheets;
}