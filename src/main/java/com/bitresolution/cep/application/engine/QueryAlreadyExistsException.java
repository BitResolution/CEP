package com.bitresolution.cep.application.engine;

import com.bitresolution.cep.application.queries.CepQuery;

public class QueryAlreadyExistsException extends RuntimeException {
    public QueryAlreadyExistsException(CepQuery query) {
        super("Query '" + query.getName() + "' already exists");
    }
}
