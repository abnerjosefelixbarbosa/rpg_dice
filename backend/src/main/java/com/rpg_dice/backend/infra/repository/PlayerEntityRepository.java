package com.rpg_dice.backend.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpg_dice.backend.infra.entity.PlayerEntity;

public interface PlayerEntityRepository extends JpaRepository<PlayerEntity, String> {

}