package com.platzi.pizza.persistence.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_order", nullable = false)
  private Integer idOrder;

  @Column(name = "id_customer", length = 15, nullable = false)
  private String idCustomer;

  @Column(nullable = false, columnDefinition = "DATETIME")
  private LocalDateTime date;

  @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
  private Double total;

  @Column(nullable = false, columnDefinition = "CHAR(1)")
  private String method;

  @Column(name = "additional_notes", length = 200)
  private String additionalNotes;

  @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
  @OrderBy("price")
  private Set<OrderItemEntity> items = new HashSet<>();

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_customer", insertable = false, updatable = false, referencedColumnName = "id_customer")
  @JsonIgnore
  private CustomerEntity customer;
}
