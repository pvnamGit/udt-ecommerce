package com.udt.udt_ecommerce.api.shared.errors;

import jakarta.persistence.metamodel.Type;

public interface ErrorCodeType extends Type<String> {
    PersistenceType getPersistenceType();
}
