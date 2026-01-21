package com.platzi.pizza.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaPagSortRepository;
import com.platzi.pizza.persistence.repository.PizzaRepository;

@Service
public class PizzaService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private PizzaRepository pizzaRepository;

  @Autowired
  private PizzaPagSortRepository pizzaPagSortRepository;

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
    return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
        .orElseThrow(() -> new RuntimeException("La pizza con nombre " + name + " no exite"));
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

  public List<PizzaEntity> getCheapest(double price) {
    return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
  }

  public Page<PizzaEntity> getAll(int page, int elements) {
    return this.pizzaPagSortRepository.findAll(PageRequest.of(page, elements));
  }

  public Page<PizzaEntity> getAvailabe(int page, int elements, String sortBy) {
    return this.pizzaPagSortRepository.findAll(PageRequest.of(page, elements, Sort.by(sortBy)));
  }

  public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String direction) {
    return this.pizzaPagSortRepository
        .findAll(PageRequest.of(page, elements, Sort.by(Sort.Direction.fromString(direction), sortBy)));
  }
}
