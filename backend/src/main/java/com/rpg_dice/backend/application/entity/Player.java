package com.rpg_dice.backend.application.entity;

import java.util.List;
import java.util.Objects;

public class Player {
	private String id;
	private String name;
	private String email;
	private List<Account> accounts;
	
	public Player() {
		super();
	}
	
	public Player(String id, String name, String email, List<Account> accounts) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.accounts = accounts;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccountDomains(List<Account> accounts) {
		this.accounts = accounts;
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
		Player other = (Player) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "PlayerDomain [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
}