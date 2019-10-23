package com.nebula.bitcoinconverter.utils;

import com.nebula.bitcoinconverter.models.BitCoinConversionRate;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConversionUtil {

    public static double calculateSellPrice(double exchangeRate, double margin, BitCoinConversionRate conversionRate) {

        double marginPrice = (margin * conversionRate.getRate()) / 100;

        double computedMarginPrice = conversionRate.getRate() - marginPrice;

        return convertToNairaAmount(exchangeRate, computedMarginPrice);
    }

    public static double calculateBuyPrice(double exchangeRate, double margin, BitCoinConversionRate conversionRate) {

        double marginPrice = (margin * conversionRate.getRate()) / 100;

        double computedMarginPrice = conversionRate.getRate() + marginPrice;

        return convertToNairaAmount(exchangeRate, computedMarginPrice);
    }

    private static double convertToNairaAmount(double exchangeRate, double amount){
        double value = exchangeRate * amount;
        BigDecimal bigDecimal = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);

        return bigDecimal.doubleValue();
    }
}
