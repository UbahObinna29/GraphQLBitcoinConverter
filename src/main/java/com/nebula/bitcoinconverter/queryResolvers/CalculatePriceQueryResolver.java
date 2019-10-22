package com.nebula.bitcoinconverter.queryResolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.nebula.bitcoinconverter.models.OperationType;


public class CalculatePriceQueryResolver implements GraphQLQueryResolver {

    public CalculatePriceQueryResolver() {
    }

    public double calculatePrice(OperationType operationType, Double margin, Double exchangeRate) {
        switch (operationType){
            case buy:
                return 1.0;
            case sell:
                return 2.0;
        }
        throw new IllegalArgumentException();
    }
}
