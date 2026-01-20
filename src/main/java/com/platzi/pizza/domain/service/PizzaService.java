package com.platzi.pizza.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaRepository;

@Service
public class PizzaService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private PizzaRepository pizzaRepository;

  public List<PizzaEntity> getAll() {
    return this.pizzaRepository.findAll();
  }

  public List<PizzaEntity> getAllUnavailable() {
    return this.jdbcTemplate.query("SELECT * FROM pizza WHERE available=true",
        new BeanPropertyRowMapper<>(PizzaEntity.class));
  }

  public PizzaEntity getById(int id) {
    return this.pizzaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("La pizza con id " + id + " no existe"));
  }

  public PizzaEntity save(PizzaEntity pizzaEntity) {
    return this.pizzaRepository.save(pizzaEntity);
  }

  public boolean exists(int idPizza) {
    return this.pizzaRepository.existsById(idPizza);
  }

  public void delete(int idPizza) {
    this.pizzaRepository.deleteById(idPizza);
  }

  public List<PizzaEntity> getAllAvailable() {
    return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
  }

  public PizzaEntity getByName(String name) {
    return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
  }

  public List<PizzaEntity> getWith(String ingredient) {
    return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
  }

  public List<PizzaEntity> getWithout(String ingredient) {
    return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
  }

  public List<PizzaEntity> lessThan(double price) {
    return this.pizzaRepository.findByPriceLessThan(price);
  }

  public List<PizzaEntity> greaterThan(double price) {
    return this.pizzaRepository.findByPriceGreaterThan(price);
  }

  public List<PizzaEntity> findPref(String prefix) {
    return this.pizzaRepository.findAllByNameStartingWith(prefix);
  }
}
