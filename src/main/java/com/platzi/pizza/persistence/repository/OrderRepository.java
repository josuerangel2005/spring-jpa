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
             po.order_date AS orderDate,
             po.total AS orderTotal,
             GROUP_CONCAT(p.name) AS pizzaNames
      FROM pixa_order po
      JOIN customer c ON po.id_customer = c.id_customer
      JOIN order_item oi ON po.id_order = oi.id_order
      JOIN pixa p ON oi.id_pixa = p.id_pixa
      WHERE po.id_order = :orderId
      GROUP BY po.id_order, c.name, po.order_date, po.total;
            """, nativeQuery = true)
  OrderSummary findSummary(@Param("orderId") int orderId);

}
