package com.platzi.pizza.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platzi.pizza.domain.dto.UpdatePizzaPriceDto;
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

  @GetMapping("/cheapest/{price}")
  public ResponseEntity<List<PizzaEntity>> getCheapest(@PathVariable double price) {
    return new ResponseEntity<List<PizzaEntity>>(this.pizzaService.getCheapest(price), HttpStatus.OK);
  }

  @GetMapping("/p")
  public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "8") int elements) {
    return new ResponseEntity<Page<PizzaEntity>>(this.pizzaService.getAll(page, elements), HttpStatus.OK);
  }

  @GetMapping("/pv")
  public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "8") int elements, @RequestParam(defaultValue = "price") String sortBy) {
    return new ResponseEntity<Page<PizzaEntity>>(this.pizzaService.getAvailabe(page, elements, sortBy), HttpStatus.OK);
  }

  @GetMapping("/pvd")
  public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "8") int elements, @RequestParam(defaultValue = "price") String sortBy,
      @RequestParam(defaultValue = "ASC") String direction) {
    return new ResponseEntity<Page<PizzaEntity>>(this.pizzaService.getAvailable(page, elements, sortBy, direction),
        HttpStatus.OK);
  }

  @PutMapping("/updatepr")
  public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto updatePizzaPriceDto) {
    if (!this.pizzaService.exists(updatePizzaPriceDto.pizzaId())) {
      return ResponseEntity.badRequest().build();
    }
    this.pizzaService.updatePrice(updatePizzaPriceDto);
    return ResponseEntity.ok().build();
  }
}
