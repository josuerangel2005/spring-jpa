package com.platzi.pizza.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import com.platzi.pizza.persistence.entity.PizzaEntity;

public interface PizzaSqlRepository extends ListCrudRepository<PizzaEntity, Integer> {
  // Consultas con JPQl
  // 1. Buscar todas las pizzas disponible
  @Query(value = """
      SELECT
            p
      FROM
            PizzaEntity p
      WHERE
            p.available = true
      """)
  List<PizzaEntity> getAll();

}
