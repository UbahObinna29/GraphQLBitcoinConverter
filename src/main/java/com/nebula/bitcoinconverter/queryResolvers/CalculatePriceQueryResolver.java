package com.nebula.bitcoinconverter.queryResolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.nebula.bitcoinconverter.models.OperationType;
import com.nebula.bitcoinconverter.services.BitCoinPriceFetcher;


public class CalculatePriceQueryResolver implements GraphQLQueryResolver {

    private BitCoinPriceFetcher bitCoinPriceFetcher;

    public CalculatePriceQueryResolver(BitCoinPriceFetcher bitCoinPriceFetcher) {
        this.bitCoinPriceFetcher = bitCoinPriceFetcher;
    }

    public double calculatePrice(OperationType operationType, Double margin, Double exchangeRate) {

        bitCoinPriceFetcher.getBitCoinConversionRate();

        switch (operationType){
            case buy:
                return 1.0;
            case sell:
                return 2.0;
        }
        throw new IllegalArgumentException();
    }
}
