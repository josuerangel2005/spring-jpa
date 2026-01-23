package com.platzi.pizza.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.platzi.pizza.persistence.entity.CustomerEntity;
import com.platzi.pizza.persistence.projection.NameProjection;
import com.platzi.pizza.persistence.projection.NameQuantity;

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

  @Query(value = """
      SELECT
      	c.name
      FROM
      	customer c
      WHERE
      	c.id_customer =
      (SELECT
      	po.id_customer As id
      FROM
      	pizza_order po
      GROUP BY
      	po.id_customer
      ORDER BY
      	SUM(po.total)
      DESC
      LIMIT
      	1);
          """, nativeQuery = true)
  NameProjection mostExpensiveCustomer();

  @Query(value = """
      SELECT
      	c.name,
      	COUNT(po.id_customer) As compras
      FROM
      	customer c INNER JOIN pizza_order po ON c.id_customer  = po.id_customer
      GROUP BY
          c.name;
            """, nativeQuery = true)
  List<NameQuantity> nameQuantities();
}
