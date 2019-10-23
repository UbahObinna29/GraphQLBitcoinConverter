package com.nebula.bitcoinconverter.services;

import com.nebula.bitcoinconverter.models.BitCoinConversionRate;

public interface BitCoinPriceFetcher {

    BitCoinConversionRate getBitCoinConversionRate();
}
