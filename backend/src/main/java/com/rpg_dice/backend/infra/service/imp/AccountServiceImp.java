package com.rpg_dice.backend.infra.service.imp;

import org.springframework.stereotype.Service;

import com.rpg_dice.backend.application.entity.Account;
import com.rpg_dice.backend.infra.entity.AccountEntity;
import com.rpg_dice.backend.infra.mapper.AccountMapper;
import com.rpg_dice.backend.infra.repository.AccountEntityRepository;
import com.rpg_dice.backend.infra.service.AccountService;
import com.rpg_dice.backend.infra.service.PlayerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountServiceImp implements AccountService {
	private final AccountEntityRepository accountEntityRepository;
	private final PlayerService playerService;
	private final AccountMapper accountMapper;
	
	public Account create(Account account) {
		AccountEntity accountEntity = accountMapper.toAccountEntity(account);
		
		playerService.save(accountEntity.getPlayerEntity());
		
		accountEntity = accountEntityRepository.save(accountEntity);
		
		return accountMapper.toAccount(accountEntity);
	}
}