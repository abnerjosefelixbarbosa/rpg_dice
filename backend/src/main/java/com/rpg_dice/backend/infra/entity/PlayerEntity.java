package com.rpg_dice.backend.infra.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "player_tb")
public class PlayerEntity {
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "name", nullable = false, unique = true, length = 100)
	private String name;
	@Column(name = "email", nullable = false, unique = true, length = 50)
	private String email;
	@OneToMany(mappedBy = "playerEntity", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
	private List<AccountEntity> accountEntities;
}