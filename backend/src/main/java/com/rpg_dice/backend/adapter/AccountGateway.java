package com.rpg_dice.backend.adapter;

import com.rpg_dice.backend.application.entity.Account;

public interface AccountGateway {
	Account create(Account account);
	
	boolean existsByPasswordOrPlayerNameOrPlayerEmail(Account account);
}