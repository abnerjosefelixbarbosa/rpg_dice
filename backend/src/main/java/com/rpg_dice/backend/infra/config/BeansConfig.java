package com.rpg_dice.backend.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rpg_dice.backend.adapter.AccountGateway;
import com.rpg_dice.backend.application.usercase.AccountUsercase;
import com.rpg_dice.backend.application.usercase.imp.AccountUsercaseImp;

@Configuration
public class BeansConfig {
	@Bean
	public AccountUsercase accountUsercase(AccountGateway accountGateway) {
		return new AccountUsercaseImp(accountGateway);
	}
}