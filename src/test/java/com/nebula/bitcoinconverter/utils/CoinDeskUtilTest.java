package com.nebula.bitcoinconverter.utils;

import com.nebula.bitcoinconverter.models.coinDesk.CoinDeskResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CoinDeskUtilTest {

    @Test
    void testUnmarshallCoinDeskJSON() {

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

        CoinDeskResponse coinDeskResponse = CoinDeskUtil.unmarshalJson(json);
        assertNotEquals(coinDeskResponse, null);
        assertEquals(coinDeskResponse.getBitCoinPriceIndex().getUsd().getRateFloat(), 8290.135);
        assertEquals(coinDeskResponse.getBitCoinPriceIndex().getUsd().getCode(), "USD");
        assertEquals(coinDeskResponse.getBitCoinPriceIndex().getUsd().getDescription(), "United States Dollar");
    }

}