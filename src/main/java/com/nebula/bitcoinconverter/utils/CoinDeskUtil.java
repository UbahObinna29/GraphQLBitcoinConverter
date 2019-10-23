package com.nebula.bitcoinconverter.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nebula.bitcoinconverter.models.coinDesk.CoinDeskResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CoinDeskUtil {

    private static Logger logger = LoggerFactory.getLogger(CoinDeskUtil.class);

    public static CoinDeskResponse unmarshalJson(String json){
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        CoinDeskResponse coinDeskResponse = null;
        try {
            coinDeskResponse = mapper.readValue(json, CoinDeskResponse.class);
            System.out.println(coinDeskResponse.getBitCoinPriceIndex().getUsd().getRateFloat());
        }catch (JsonProcessingException ex){
            logger.error("Unable to unmarshall JSON to POJO",ex);
        }

        return coinDeskResponse;
    }
}
