package com.rpg_dice.backend.infra.service.imp;

import org.springframework.stereotype.Service;

import com.rpg_dice.backend.infra.entity.PlayerEntity;
import com.rpg_dice.backend.infra.repository.PlayerEntityRepository;
import com.rpg_dice.backend.infra.service.PlayerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PlayerServiceImp implements PlayerService {
	private final PlayerEntityRepository playerEntityRepository;
	
	public PlayerEntity save(PlayerEntity playerEntity) {
		return playerEntityRepository.save(playerEntity);
	}
}