package com.platzi.pizza.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.platzi.pizza.persistence.entity.CustomerEntity;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity, Integer> {
  @Query(value = "SELECT c FROM CustomerEntity c WHERE c.phoneNumber = :phone")
  CustomerEntity findByPhone(@Param("phone") String phone);

  // 3.Buscar clientes cuyo nombre contenga un texto específico
  @Query(value = """
      SELECT
            c
      FROM
            CustomerEntity c
      WHERE
            c.name LIKE :text
      """)
  List<CustomerEntity> findByText(@Param("text") String text);
}
