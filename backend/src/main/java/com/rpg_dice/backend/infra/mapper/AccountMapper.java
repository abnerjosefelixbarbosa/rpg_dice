package com.rpg_dice.backend.infra.mapper;

import com.rpg_dice.backend.application.entity.Account;
import com.rpg_dice.backend.infra.dto.AccountRequestDTO;

public interface AccountMapper {
	Account toAccount(AccountRequestDTO dto);
}