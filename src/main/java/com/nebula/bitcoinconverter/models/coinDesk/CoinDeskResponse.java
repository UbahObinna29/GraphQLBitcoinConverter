package com.nebula.bitcoinconverter.models.coinDesk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinDeskResponse {

    @JsonProperty("bpi")
    private BitCoinPriceIndex bitCoinPriceIndex;

    public BitCoinPriceIndex getBitCoinPriceIndex() {
        return bitCoinPriceIndex;
    }

    public void setBitCoinPriceIndex(BitCoinPriceIndex bitCoinPriceIndex) {
        this.bitCoinPriceIndex = bitCoinPriceIndex;
    }
}
