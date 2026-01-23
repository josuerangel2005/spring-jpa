package com.platzi.pizza.domain.exception;

public class EmailApiException extends RuntimeException {

  public EmailApiException() {
    super("Error sending email...");
  }
}
