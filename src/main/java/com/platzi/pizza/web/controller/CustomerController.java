package com.platzi.pizza.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platzi.pizza.domain.service.CustomerService;
import com.platzi.pizza.persistence.entity.CustomerEntity;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @GetMapping("/{phone}")
  public ResponseEntity<CustomerEntity> getAll(@PathVariable String phone) {
    return new ResponseEntity<CustomerEntity>(this.customerService.findByPhone(phone), HttpStatus.OK);
  }

  @GetMapping("/srch/{text}")
  public ResponseEntity<List<CustomerEntity>> findByText(@PathVariable String text) {
    return ResponseEntity.ok(this.customerService.findByText(text));
  }
}
