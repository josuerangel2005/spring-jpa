package com.platzi.pizza.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

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

  // 2. Buscar pizzas vegetarianas con precio menor a un valor dado
  @Query(value = """
      SELECT
            p
      FROM
          PizzaEntity p
      WHERE
          p.vegetarian = true AND p.price < :price
        """)
  List<PizzaEntity> getVegetarianAndLessThan(@Param("price") double price);

  // 4. Contar cuántas pizzas veganas hay en total
  @Query(value = """
      SELECT
            COUNT(p)
      FROM
            PizzaEntity p
      WHERE
            p.vegan = true
      """)
  Long countVegan();

}
