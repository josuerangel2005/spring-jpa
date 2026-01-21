package com.platzi.pizza.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.platzi.pizza.persistence.entity.PizzaEntity;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
  List<PizzaEntity> findByPriceLessThan(double price);

  List<PizzaEntity> findByPriceGreaterThan(double price);

  List<PizzaEntity> findAllByNameStartingWith(String prefix);

  List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

  Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

  List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

  List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

  List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);
}
