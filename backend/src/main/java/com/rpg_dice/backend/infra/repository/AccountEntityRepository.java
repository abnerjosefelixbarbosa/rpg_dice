package com.rpg_dice.backend.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpg_dice.backend.infra.entity.AccountEntity;

public interface AccountEntityRepository extends JpaRepository<AccountEntity, String> {
	boolean existsByPasswordOrPlayerEntityNameOrPlayerEntityEmail(String password, String name, String email);
}