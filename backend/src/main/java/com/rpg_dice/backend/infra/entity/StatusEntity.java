package com.rpg_dice.backend.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class StatusEntity {
	@Column(name = "strength", nullable = false)
	private Long strength;
	@Column(name = "dexterity", nullable = false)
	private Long dexterity;
	@Column(name = "constitution", nullable = false)
	private Long constitution;
	@Column(name = "intelligence", nullable = false)
	private Long intelligence;
	@Column(name = "charisma", nullable = false)
	private Long charisma;
	@Column(name = "wisdom", nullable = false)
	private Long wisdom;
}