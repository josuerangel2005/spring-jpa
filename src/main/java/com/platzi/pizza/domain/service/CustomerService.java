package com.platzi.pizza.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platzi.pizza.persistence.entity.CustomerEntity;
import com.platzi.pizza.persistence.projection.NameProjection;
import com.platzi.pizza.persistence.projection.NameQuantity;
import com.platzi.pizza.persistence.repository.CustomerRepository;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  public CustomerEntity findByPhone(String phone) {
    return this.customerRepository.findByPhone(phone);
  }

  public List<CustomerEntity> findByText(String text) {
    return this.customerRepository.findByText("%" + text + "%");
  }

  public NameProjection mostExpensiveCustomer() {
    return this.customerRepository.mostExpensiveCustomer();
  }

  public List<NameQuantity> nameQuantities() {
    return this.customerRepository.nameQuantities();
  }
}
