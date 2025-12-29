package com.rpg_dice.backend.infra.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "item_tb")
public class ItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "description", nullable = false, length = 100)
	private String description;
	@Column(name = "quantity", nullable = false)
	private Long quantity;
	@ManyToOne
	@JoinColumn(name = "sheet_id", nullable = false)
	private SheetEntity sheet;
}