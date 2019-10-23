package com.nebula.bitcoinconverter.utils;

import com.nebula.bitcoinconverter.models.BitCoinConversionRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConversionUtilTest {

    private BitCoinConversionRate bitCoinConversionRate = new BitCoinConversionRate();
    private double exchangeRate;
    private double margin;

    @BeforeEach
    void prepare() {
        bitCoinConversionRate.setRate(8290.135);
        exchangeRate = 360;
        margin = 20;
    }


    @Test
    void testCalculateBuyPrice() {
        Double myDub = ConversionUtil.calculateBuyPrice(exchangeRate, margin, bitCoinConversionRate);
        assertEquals(myDub, 3581338.32);
    }

    @Test
    void testCalculateSellPrice() {
        Double myDub = ConversionUtil.calculateSellPrice(exchangeRate, margin, bitCoinConversionRate);
        assertEquals(myDub, 2387558.88);
    }

}