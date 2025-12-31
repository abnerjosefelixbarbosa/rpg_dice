package com.rpg_dice.backend.infra.mapper.imp;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.rpg_dice.backend.application.entity.Account;
import com.rpg_dice.backend.application.entity.Player;
import com.rpg_dice.backend.infra.dto.AccountRequestDTO;
import com.rpg_dice.backend.infra.dto.AccountResponseDTO;
import com.rpg_dice.backend.infra.mapper.AccountMapper;

@Component
public class AccountMapperImp implements AccountMapper{
	public Account toAccount(AccountRequestDTO dto) {
		return new Account(
				getId(),
				dto.password(),
				new Player(
						getId(),
						dto.playerName(),
						dto.playerEmail(),
						null
			    ),
		        null
		 );
	}
	
	public AccountResponseDTO toAccountResponseDTO(Account account) {
		return new AccountResponseDTO(
				null,
				null,
				null
		 );
	}
	
	private String getId() {
		return UUID.randomUUID().toString();
	}
}