package com.platzi.pizza.persistence.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.platzi.pizza.persistence.entity.PizzaEntity;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
  List<PizzaEntity> findByPriceLessThan(double price);

  List<PizzaEntity> findByPriceGreaterThan(double price);

  List<PizzaEntity> findAllByNameStartingWith(String prefix);

  List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

  PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);

  List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

  List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
}
