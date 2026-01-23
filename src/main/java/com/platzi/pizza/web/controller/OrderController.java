package com.platzi.pizza.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platzi.pizza.domain.service.OrderService;
import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.projection.OrderSummary;
import com.platzi.pizza.persistence.projection.PaymentMethod;

@RestController
@RequestMapping("/api/order")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @GetMapping
  public ResponseEntity<List<OrderEntity>> getAll() {
    return new ResponseEntity<List<OrderEntity>>(this.orderService.getAll(), HttpStatus.OK);
  }

  @GetMapping("/today")
  public ResponseEntity<List<OrderEntity>> getTodayOrders() {
    return new ResponseEntity<List<OrderEntity>>(this.orderService.getTodayOrders(), HttpStatus.OK);
  }

  @GetMapping("/outside")
  public ResponseEntity<List<OrderEntity>> getOutsideOrders() {
    return new ResponseEntity<List<OrderEntity>>(this.orderService.getOutsideOrders(), HttpStatus.OK);
  }

  @GetMapping("/customer/{id}")
  public ResponseEntity<List<OrderEntity>> getOrderCustomer(@PathVariable String idCustomer) {
    return new ResponseEntity<List<OrderEntity>>(this.orderService.getCustomerOrders(idCustomer), HttpStatus.OK);
  }

  @GetMapping("/summary/{id}")
  public ResponseEntity<OrderSummary> getSummary(@PathVariable int id) {
    return new ResponseEntity<OrderSummary>(this.orderService.getSummary(id), HttpStatus.OK);
  }

  @GetMapping("/past")
  public ResponseEntity<List<OrderEntity>> getAllPast(@RequestParam LocalDateTime date) {
    return ResponseEntity.ok(this.orderService.getAllPast(date));
  }

  @GetMapping("/cusn")
  public ResponseEntity<List<OrderEntity>> getAllByCustomer(@RequestParam String name) {
    return ResponseEntity.ok(this.orderService.getAllByCustomer(name));
  }

  @GetMapping("/total")
  public ResponseEntity<Double> getTotal() {
    return ResponseEntity.ok(this.orderService.getTotal());
  }

  @GetMapping("/payments")
  public ResponseEntity<List<PaymentMethod>> getPayments() {
    return ResponseEntity.ok(this.orderService.getPayments());
  }
}
