package com.platzi.pizza.domain.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platzi.pizza.domain.dto.RandomOrderDto;
import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.projection.OrderSummary;
import com.platzi.pizza.persistence.projection.PaymentMethod;
import com.platzi.pizza.persistence.repository.OrderRepository;

import jakarta.transaction.Transactional;

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

  public List<OrderEntity> getAllPast(LocalDateTime date) {
    return this.orderRepository.getAllPast(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date));
  }

  public List<OrderEntity> getAllByCustomer(String name) {
    System.out.println("\n" + name);
    return this.orderRepository
        .getAllByCustomer(Arrays.stream(name.toLowerCase().split("%20")).collect(Collectors.joining(" ")));
  }

  public Double getTotal() {
    return this.orderRepository.getTotal();
  }

  public List<PaymentMethod> getPayments() {
    return this.orderRepository.getPayments();
  }

  @Transactional
  public Boolean saveRandomOrder(RandomOrderDto randomOrderDto) {
    return this.orderRepository.saveRandomOrder(randomOrderDto.idCustomer(), randomOrderDto.method());

  }
}
