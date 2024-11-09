package com.udt.udt_ecommerce.api.shared.errors;

import jakarta.persistence.metamodel.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorCodeType {
  E001("E001", "Unauthenticated"),
  E002("E002", "Bad Request Payload"),
  E003("E003", "Unauthorized"),
  E004("E004", "Record not found"),
  E005("E005", "Internal Server Error"),
  E006("E006", "Invalid Request Payload"),
  E007("E007", "Constraint Violation"),
  E008("E008", "Bad Credentials"),
  E009("E009", "Data Integrity Violation"),
  E010("E010", "Illegal Argument Exception");

  final String code;
  final String message;



  @Override
  public Class<String> getJavaType() {
    return null;
  }

  @Override
  public PersistenceType getPersistenceType() {
    return null;
  }
}
