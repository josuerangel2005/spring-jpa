package com.platzi.pizza.domain.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.projection.OrderSummary;
import com.platzi.pizza.persistence.repository.OrderRepository;

@Service
public class OrderService {
  private static final String DELIVERY = "D";
  private static final String CARRYOUT = "C";

  @Autowired
  private OrderRepository orderRepository;

  public List<OrderEntity> getAll() {
    return this.orderRepository.findAll();
  }

  public List<OrderEntity> getTodayOrders() {
    return this.orderRepository.findAllByDateAfter(LocalDate.now().atTime(0, 0));
  }

  public List<OrderEntity> getOutsideOrders() {
    return this.orderRepository.findAllByMethodIn(Arrays.asList(DELIVERY, CARRYOUT));
  }

  public List<OrderEntity> getCustomerOrders(String idCustomer) {
    return this.orderRepository.findCustomerOrders(idCustomer);
  }

  public OrderSummary getSummary(int orderId) {
    return this.orderRepository.findSummary(orderId);
  }
}
