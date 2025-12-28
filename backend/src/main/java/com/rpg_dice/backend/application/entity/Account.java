package com.rpg_dice.backend.application.entity;

import java.util.List;
import java.util.Objects;

public class Account {
	private String id;
	private String password;
	private Player player;
	private List<Sheet> sheets;
	
	public Account() {
		super();
	}
	
	public Account(String id, String password, Player player, List<Sheet> sheets) {
		super();
		this.id = id;
		this.password = password;
		this.player = player;
		this.sheets = sheets;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayerDomain(Player player) {
		this.player = player;
	}

	public List<Sheet> getSheet() {
		return sheets;
	}

	public void setSheet(List<Sheet> sheets) {
		this.sheets = sheets;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "AccountDomain [id=" + id + ", password=" + password + ", player=" + player.getName() + "]";
	}
}