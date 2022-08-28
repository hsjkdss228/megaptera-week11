package com.inu.user.exceptions;

import javax.persistence.EntityNotFoundException;

public class LoginFailed extends RuntimeException {
  private final RuntimeException exception;

  public LoginFailed() {
    this.exception = null;
  }

  public LoginFailed(EntityNotFoundException exception) {
    this.exception = exception;
  }
}
