package com.rpg_dice.backend.infra.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "sheet_tb")
public class SheetEntity {
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	@Column(name = "age", nullable = false)
	private Long age;
	@Column(name = "specie", nullable = false)
	private String specie;
	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private GenderTypeEntity genderTypeEntity;
	@Column(name = "country", nullable = false)
	private String country;
	@Embedded
	private StatusEntity status;
	@Column(name = "hp", nullable = false)
	private Long hp;
	@Column(name = "ac", nullable = false)
	private Long ac;
	@Column(name = "xp", nullable = false)
	private Long xp;
	@Column(name = "level", nullable = false)
	private Long level;
	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private AccountEntity accountEntity;
	@OneToMany(mappedBy = "sheetEntity", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
	private List<ItemEntity> itemEntities;
}