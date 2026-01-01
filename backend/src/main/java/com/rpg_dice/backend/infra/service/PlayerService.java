package com.rpg_dice.backend.infra.service;

import com.rpg_dice.backend.infra.entity.PlayerEntity;

public interface PlayerService {
	PlayerEntity save(PlayerEntity playerEntity);
}