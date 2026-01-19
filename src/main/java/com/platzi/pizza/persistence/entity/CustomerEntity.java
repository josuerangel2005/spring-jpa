package com.platzi.pizza.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_customer", nullable = false)
  private Integer idCustomer;

  @Column(nullable = false, unique = true, length = 60)
  private String name;

  @Column(nullable = false, length = 100)
  private String address;

  @Column(nullable = false, length = 50)
  private String email;

  @Column(nullable = false, length = 20)
  private String phoneNumber;

}
