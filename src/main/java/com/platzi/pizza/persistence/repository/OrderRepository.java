package com.platzi.pizza.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.projection.OrderSummary;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
  List<OrderEntity> findAllByDateAfter(LocalDateTime date);

  List<OrderEntity> findAllByMethodIn(List<String> methods);

  @Query(value = "SELECT * FROM pizza_order WHERE  id_customer = :id", nativeQuery = true)
  List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);

  @Query(value = """
      SELECT po.id_order AS idOrder,
             c.name AS customerName,
             po.date AS orderDate,
             po.total AS orderTotal,
             GROUP_CONCAT(p.name) AS pizzaNames
      FROM pizza_order po
      JOIN customer c ON po.id_customer = c.id_customer
      JOIN order_item oi ON po.id_order = oi.id_order
      JOIN pizza p ON oi.id_pizza = p.id_pizza
      WHERE po.id_order = :orderId
      GROUP BY po.id_order, c.name, po.date, po.total;
            """, nativeQuery = true)
  OrderSummary findSummary(@Param("orderId") int orderId);

  // Buscar todas las órdenes realizadas después de una fecha específica
  @Query(value = """
      SELECT
        o
      FROM
        OrderEntity o
      WHERE
        o.date > :date
        """)
  List<OrderEntity> getAllPast(@Param("date") String date);

  // Buscar órdenes de un cliente específico usando el nombre del cliente

  @Query(value = """
      SELECT
          o
      FROM
          OrderEntity o INNER JOIN o.customer c
      WHERE
          c.name = :name
      """)
  List<OrderEntity> getAllByCustomer(@Param("name") String name);
}
