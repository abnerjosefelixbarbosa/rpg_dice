package com.rpg_dice.backend.infra.mapper;

import com.rpg_dice.backend.application.entity.Account;
import com.rpg_dice.backend.infra.dto.AccountRequestDTO;
import com.rpg_dice.backend.infra.dto.AccountResponseDTO;
import com.rpg_dice.backend.infra.entity.AccountEntity;

public interface AccountMapper {
	Account toAccount(AccountRequestDTO dto);
	
	AccountResponseDTO toAccountResponseDTO(Account account);
	
	AccountEntity toAccountEntity(Account account);

	Account toAccount(AccountEntity accountEntity);
}