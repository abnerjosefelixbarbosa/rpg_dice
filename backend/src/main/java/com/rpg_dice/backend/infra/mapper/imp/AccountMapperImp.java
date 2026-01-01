package com.rpg_dice.backend.infra.mapper.imp;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.rpg_dice.backend.application.entity.Account;
import com.rpg_dice.backend.application.entity.Player;
import com.rpg_dice.backend.infra.dto.AccountRequestDTO;
import com.rpg_dice.backend.infra.dto.AccountResponseDTO;
import com.rpg_dice.backend.infra.entity.AccountEntity;
import com.rpg_dice.backend.infra.entity.PlayerEntity;
import com.rpg_dice.backend.infra.mapper.AccountMapper;

@Component
public class AccountMapperImp implements AccountMapper {
	public Account toAccount(AccountRequestDTO dto) {
		return new Account(UUID.randomUUID().toString(), dto.password(),
				new Player(UUID.randomUUID().toString(), dto.playerName(), dto.playerEmail(), null), null);
	}

	public AccountResponseDTO toAccountResponseDTO(Account account) {
		return new AccountResponseDTO(account.getId(), account.getPassword(), account.getPlayer().getId(),
				account.getPlayer().getName(), account.getPlayer().getEmail());
	}

	public AccountEntity toAccountEntity(Account account) {
		return new AccountEntity(account.getId(), account.getPassword(), new PlayerEntity(account.getPlayer().getId(),
				account.getPlayer().getName(), account.getPlayer().getEmail(), null), null);
	}

	public Account toAccount(AccountEntity accountEntity) {
		return new Account(
				accountEntity.getId(), accountEntity.getPassword(), new Player(accountEntity.getPlayerEntity().getId(),
						accountEntity.getPlayerEntity().getName(), accountEntity.getPlayerEntity().getEmail(), null),
				null);
	}
}