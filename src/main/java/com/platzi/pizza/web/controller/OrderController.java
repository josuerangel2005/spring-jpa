package com.platzi.pizza.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platzi.pizza.domain.service.OrderService;
import com.platzi.pizza.persistence.entity.OrderEntity;

@RestController
@RequestMapping("/api/order")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @GetMapping
  public ResponseEntity<List<OrderEntity>> getAll() {
    return new ResponseEntity<List<OrderEntity>>(this.orderService.getAll(), HttpStatus.OK);
  }
}
