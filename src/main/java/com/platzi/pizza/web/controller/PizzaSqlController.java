package com.platzi.pizza.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platzi.pizza.domain.service.PizzaSqlService;
import com.platzi.pizza.persistence.entity.PizzaEntity;

@RestController
@RequestMapping("/api/sql/pizza")
public class PizzaSqlController {
  @Autowired
  private PizzaSqlService pizzaSqlService;

  @GetMapping
  public ResponseEntity<List<PizzaEntity>> getAll() {
    return ResponseEntity.ok(this.pizzaSqlService.getAll());
  }

  @GetMapping("/vlp/{price}")
  public ResponseEntity<List<PizzaEntity>> getVegetarianAndLessThan(@PathVariable double price) {
    return ResponseEntity.ok(this.pizzaSqlService.getVegetarianAndLessThan(price));
  }

  @GetMapping("/co")
  public ResponseEntity<Long> countVegan() {
    return ResponseEntity.ok(this.pizzaSqlService.countVegan());
  }
}
