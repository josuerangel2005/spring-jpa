package com.platzi.pizza.persistence.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemPK implements Serializable {
  private Integer idOrder;
  private Integer idItem;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idOrder == null) ? 0 : idOrder.hashCode());
    result = prime * result + ((idItem == null) ? 0 : idItem.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    OrderItemPK other = (OrderItemPK) obj;
    if (idOrder == null) {
      if (other.idOrder != null)
        return false;
    } else if (!idOrder.equals(other.idOrder))
      return false;
    if (idItem == null) {
      if (other.idItem != null)
        return false;
    } else if (!idItem.equals(other.idItem))
      return false;
    return true;
  }

}
