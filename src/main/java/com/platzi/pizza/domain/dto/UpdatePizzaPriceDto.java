package com.platzi.pizza.domain.dto;

public record UpdatePizzaPriceDto(
    int pizzaId,
    double newPrice) {
}
