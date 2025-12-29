package com.rpg_dice.backend.infra.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "account_tb")
public class AccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "password", nullable = false, length = 100)
	private String password;
	@ManyToOne
	@JoinColumn(name = "player_id", nullable = false)
	private PlayerEntity playerEntity;	
	@OneToMany(mappedBy = "accountEntity", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
	private List<SheetEntity> sheetEntities;
}