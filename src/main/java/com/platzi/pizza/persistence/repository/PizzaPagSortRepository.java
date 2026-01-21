package com.platzi.pizza.persistence.repository;

import org.springframework.data.repository.ListPagingAndSortingRepository;

import com.platzi.pizza.persistence.entity.PizzaEntity;

public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer> {

}
