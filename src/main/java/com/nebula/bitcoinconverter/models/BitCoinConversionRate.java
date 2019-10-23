package com.nebula.bitcoinconverter.models;

import com.nebula.bitcoinconverter.models.coinDesk.BitCoinPriceIndex;

public class BitCoinConversionRate {

    public BitCoinConversionRate(BitCoinPriceIndex bitCoinPriceIndex) {
        this.setCurrencyCode(bitCoinPriceIndex.getUsd().getCode());
        this.setCurrencySymbol(bitCoinPriceIndex.getUsd().getSymbol());
        this.setRate(bitCoinPriceIndex.getUsd().getRateFloat());
    }

    public BitCoinConversionRate() {
    }

    private String currencyCode;

    private String currencySymbol;

    private double rate;

    public String getCurrencyCode() {
        return currencyCode;
    }

    private void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    private void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
