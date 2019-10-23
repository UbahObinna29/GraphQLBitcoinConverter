package com.nebula.bitcoinconverter.exceptions;

import com.nebula.bitcoinconverter.models.api.ResponseCode;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ApiException extends RuntimeException implements GraphQLError {

    private final int errorCode;

    public ApiException(ResponseCode responseCode) {
        super(responseCode.getMessage());

        this.errorCode = responseCode.getCode();
    }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> customAttributes = new LinkedHashMap<>();

        customAttributes.put("errorCode", this.errorCode);
        customAttributes.put("errorMessage", this.getMessage());

        return customAttributes;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }

}
