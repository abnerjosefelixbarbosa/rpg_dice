package com.rpg_dice.backend.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpg_dice.backend.infra.entity.ItemEntity;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, String> {

}