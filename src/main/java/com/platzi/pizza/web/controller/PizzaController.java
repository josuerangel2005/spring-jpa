package com.platzi.pizza.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platzi.pizza.domain.service.PizzaService;
import com.platzi.pizza.persistence.entity.PizzaEntity;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

  @Autowired
  private PizzaService pizzaService;

  @GetMapping
  public ResponseEntity<List<PizzaEntity>> getAll() {
    return new ResponseEntity<List<PizzaEntity>>(this.pizzaService.getAll(), HttpStatus.OK);
  }

  @GetMapping("/u")
  public ResponseEntity<List<PizzaEntity>> getAllU() {
    return new ResponseEntity<List<PizzaEntity>>(this.pizzaService.getAllUnavailable(), HttpStatus.OK);
  }

  @GetMapping("/a")
  public ResponseEntity<List<PizzaEntity>> getAllAvailabe() {
    return new ResponseEntity<List<PizzaEntity>>(this.pizzaService.getAllAvailable(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PizzaEntity> getById(@PathVariable int id) {
    return new ResponseEntity<PizzaEntity>(this.pizzaService.getById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizzaEntity) {
    if (pizzaEntity.getIdPizza() == null || !this.pizzaService.exists(pizzaEntity.getIdPizza()))
      return new ResponseEntity<PizzaEntity>(this.pizzaService.save(pizzaEntity), HttpStatus.OK);
    return ResponseEntity.badRequest().build();
  }

  @PutMapping
  public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizzaEntity) {
    if (pizzaEntity.getIdPizza() != null && this.pizzaService.exists(pizzaEntity.getIdPizza()))
      return new ResponseEntity<PizzaEntity>(this.pizzaService.save(pizzaEntity), HttpStatus.OK);

    return ResponseEntity.badRequest().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable int id) {
    if (!pizzaService.exists(id))
      return ResponseEntity.badRequest().build();
    this.pizzaService.delete(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<PizzaEntity> getByName(@PathVariable String name) {
    return new ResponseEntity<PizzaEntity>(this.pizzaService.getByName(name), HttpStatus.OK);
  }

  @GetMapping("/with/{ingredient}")
  public ResponseEntity<List<PizzaEntity>> getWith(@PathVariable String ingredient) {
    return new ResponseEntity<List<PizzaEntity>>(this.pizzaService.getWith(ingredient), HttpStatus.OK);
  }

  @GetMapping("/without/{ingredient}")
  public ResponseEntity<List<PizzaEntity>> getWithout(@PathVariable String ingredient) {
    return new ResponseEntity<List<PizzaEntity>>(this.pizzaService.getWithout(ingredient), HttpStatus.OK);
  }

  @GetMapping("/less/{price}")
  public ResponseEntity<List<PizzaEntity>> getLessThan(@PathVariable double price) {
    return new ResponseEntity<List<PizzaEntity>>(this.pizzaService.lessThan(price), HttpStatus.OK);
  }

  @GetMapping("/greater/{price}")
  public ResponseEntity<List<PizzaEntity>> getGreater(@PathVariable double price) {
    return new ResponseEntity<List<PizzaEntity>>(this.pizzaService.greaterThan(price), HttpStatus.OK);
  }

  @GetMapping("/pref/{prefix}")
  public ResponseEntity<List<PizzaEntity>> getPrefix(@PathVariable String prefix) {
    return new ResponseEntity<List<PizzaEntity>>(this.pizzaService.findPref(prefix), HttpStatus.OK);
  }
}
