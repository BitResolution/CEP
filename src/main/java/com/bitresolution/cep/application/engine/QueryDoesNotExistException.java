package com.bitresolution.cep.application.engine;

import com.bitresolution.cep.application.queries.CepQuery;

public class QueryDoesNotExistException extends RuntimeException {
    public QueryDoesNotExistException(CepQuery query) {
        super("Query '" + query.getName() + "' does not exist");
    }
}
