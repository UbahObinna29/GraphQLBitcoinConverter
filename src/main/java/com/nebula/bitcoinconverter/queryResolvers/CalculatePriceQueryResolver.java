package com.nebula.bitcoinconverter.queryResolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.nebula.bitcoinconverter.models.BitCoinConversionRate;
import com.nebula.bitcoinconverter.models.OperationType;
import com.nebula.bitcoinconverter.services.BitCoinPriceFetcher;
import com.nebula.bitcoinconverter.utils.ConversionUtil;


public class CalculatePriceQueryResolver implements GraphQLQueryResolver {

    private BitCoinPriceFetcher bitCoinPriceFetcher;

    public CalculatePriceQueryResolver(BitCoinPriceFetcher bitCoinPriceFetcher) {
        this.bitCoinPriceFetcher = bitCoinPriceFetcher;
    }

    public double calculatePrice(OperationType operationType, Double margin, Double exchangeRate) {

        BitCoinConversionRate bitCoinConversionRate = bitCoinPriceFetcher.getBitCoinConversionRate();

        switch (operationType){
            case buy:
                return ConversionUtil.calculateBuyPrice(exchangeRate, margin, bitCoinConversionRate);
            case sell:
                return ConversionUtil.calculateSellPrice(exchangeRate, margin, bitCoinConversionRate);
        }
        throw new IllegalArgumentException();
    }
}
