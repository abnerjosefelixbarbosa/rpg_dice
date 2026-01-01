package com.rpg_dice.backend.application.usercase.imp;

import com.rpg_dice.backend.adapter.AccountGateway;
import com.rpg_dice.backend.application.entity.Account;
import com.rpg_dice.backend.application.usercase.AccountUsercase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountUsercaseImp implements AccountUsercase {
	private final AccountGateway accountGateway;
	
	public Account create(Account account) {
		return accountGateway.create(account);
	}
}