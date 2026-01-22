package com.platzi.pizza.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaSqlRepository;

@Service
public class PizzaSqlService {
  @Autowired
  private PizzaSqlRepository pizzaSqlRepository;

  public List<PizzaEntity> getAll() {
    return this.pizzaSqlRepository.getAll();
  }

  public List<PizzaEntity> getVegetarianAndLessThan(double price) {
    return this.pizzaSqlRepository.getVegetarianAndLessThan(price);
  }

  public Long countVegan() {
    return this.pizzaSqlRepository.countVegan();
  }
}
