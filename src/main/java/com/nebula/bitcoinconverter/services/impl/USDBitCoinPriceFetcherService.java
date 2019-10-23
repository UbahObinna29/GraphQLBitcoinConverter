package com.nebula.bitcoinconverter.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nebula.bitcoinconverter.models.BitCoinConversionRate;
import com.nebula.bitcoinconverter.models.coinDesk.BitCoinPriceIndex;
import com.nebula.bitcoinconverter.models.coinDesk.CoinDeskResponse;
import com.nebula.bitcoinconverter.services.BitCoinPriceFetcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Primary
public class USDBitCoinPriceFetcherService implements BitCoinPriceFetcher {

    @Value("${coin-desk-api-url:https://api.coindesk.com/v1/bpi/currentprice.json}")
    private String coinDeskApiUrl;

    @Override
    public BitCoinConversionRate getBitCoinConversionRate() {

        testMapping();

        ResponseEntity<String> responseEntity = callCoinDeskApi(coinDeskApiUrl);

        if (responseEntity.getStatusCode()!= HttpStatus.OK){
            //TODO Add Server Error Exception
            throw new RuntimeException();
        }

        Gson gson = new Gson();
        BitCoinPriceIndex bitCoinPriceIndex = gson.fromJson(responseEntity.getBody(), BitCoinPriceIndex.class);

        return new BitCoinConversionRate(bitCoinPriceIndex);

    }

    //TODO Add test to ensure endpoint is up
    private ResponseEntity<String> callCoinDeskApi(String coinDeskApiUrl) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(coinDeskApiUrl, String.class);
    }

    private void testMapping() {
        String json = "{\n" +
                "    \"time\": {\n" +
                "        \"updated\": \"Oct 22, 2019 10:14:00 UTC\",\n" +
                "        \"updatedISO\": \"2019-10-22T10:14:00+00:00\",\n" +
                "        \"updateduk\": \"Oct 22, 2019 at 11:14 BST\"\n" +
                "    },\n" +
                "    \"disclaimer\": \"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",\n" +
                "    \"chartName\": \"Bitcoin\",\n" +
                "    \"bpi\": {\n" +
                "        \"USD\": {\n" +
                "            \"code\": \"USD\",\n" +
                "            \"symbol\": \"&#36;\",\n" +
                "            \"rate\": \"8,290.1350\",\n" +
                "            \"description\": \"United States Dollar\",\n" +
                "            \"rate_float\": 8290.135\n" +
                "        },\n" +
                "        \"GBP\": {\n" +
                "            \"code\": \"GBP\",\n" +
                "            \"symbol\": \"&pound;\",\n" +
                "            \"rate\": \"6,410.8692\",\n" +
                "            \"description\": \"British Pound Sterling\",\n" +
                "            \"rate_float\": 6410.8692\n" +
                "        },\n" +
                "        \"EUR\": {\n" +
                "            \"code\": \"EUR\",\n" +
                "            \"symbol\": \"&euro;\",\n" +
                "            \"rate\": \"7,439.1278\",\n" +
                "            \"description\": \"Euro\",\n" +
                "            \"rate_float\": 7439.1278\n" +
                "        }\n" +
                "    }\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        CoinDeskResponse coinDeskResponse;
        try {
            coinDeskResponse = mapper.readValue(json, CoinDeskResponse.class);
            System.out.println(coinDeskResponse.getBitCoinPriceIndex().getUsd().getRateFloat());
        }catch (JsonProcessingException ex){
        }
    }
}
