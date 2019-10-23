package com.nebula.bitcoinconverter.models.coinDesk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.nebula.bitcoinconverter.models.coinDesk.currencies.USD;

public class BitCoinPriceIndex {

    @JsonProperty("USD")
    private USD usd;

    public USD getUsd() {
        return usd;
    }

    public void setUsd(USD usd) {
        this.usd = usd;
    }
}
