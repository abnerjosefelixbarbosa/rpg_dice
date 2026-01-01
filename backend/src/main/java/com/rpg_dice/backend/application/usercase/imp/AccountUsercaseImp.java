package com.rpg_dice.backend.application.usercase.imp;

import com.rpg_dice.backend.adapter.AccountGateway;
import com.rpg_dice.backend.application.entity.Account;
import com.rpg_dice.backend.application.exception.BusinessException;
import com.rpg_dice.backend.application.usercase.AccountUsercase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountUsercaseImp implements AccountUsercase {
	private final AccountGateway accountGateway;

	public Account create(Account account) {
		validate(account);

		return accountGateway.create(account);
	}

	private void validate(Account account) {
		boolean exists = accountGateway.existsByPasswordOrPlayerNameOrPlayerEmail(account);

		if (exists) {
			throw new BusinessException("Senha, nome do jogador ou email do jogador não deve ser repetido.");
		}
	}
}