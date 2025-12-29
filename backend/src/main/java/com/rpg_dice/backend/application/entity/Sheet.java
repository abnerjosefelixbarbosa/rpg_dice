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
public class Sheet {
	private String id;
	private String name;
	private Long age;
	private String specie;
	private String gender;
	private String country;
	private Status status;
	private Long hp;
	private Long ac;
	private Long xp;
	private Long level;
	private Account account;
	private List<Item> items;
}