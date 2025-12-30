package com.rpg_dice.backend.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpg_dice.backend.infra.entity.SheetEntity;

public interface SheetEntityRepository extends JpaRepository<SheetEntity, String> {

}