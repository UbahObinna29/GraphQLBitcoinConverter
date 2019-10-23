package com.nebula.bitcoinconverter.services.impl;

import com.nebula.bitcoinconverter.exceptions.ApiException;
import com.nebula.bitcoinconverter.models.BitCoinConversionRate;
import com.nebula.bitcoinconverter.models.api.ResponseCode;
import com.nebula.bitcoinconverter.models.coinDesk.CoinDeskResponse;
import com.nebula.bitcoinconverter.services.BitCoinPriceFetcher;
import com.nebula.bitcoinconverter.utils.CoinDeskUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
@Primary
public class USDBitCoinPriceFetcherService implements BitCoinPriceFetcher {

    @Value("${coin-desk-api-url:https://api.coindesk.com/v1/bpi/currentprice.json}")
    private String coinDeskApiUrl;

    private Logger logger = LoggerFactory.getLogger(USDBitCoinPriceFetcherService.class);

    @Override
    public BitCoinConversionRate getBitCoinConversionRate() {

        ResponseEntity<String> responseEntity = callCoinDeskApi(coinDeskApiUrl);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            logger.error(ResponseCode.A1.getMessage());
            throw new ApiException(ResponseCode.A1);
        }

        if (StringUtils.isEmpty(responseEntity.getBody())) {
            logger.error(ResponseCode.A2.getMessage());
            throw new ApiException(ResponseCode.A2);
        }

        CoinDeskResponse coinDeskResponse = CoinDeskUtil.unmarshalJson(responseEntity.getBody());

        return new BitCoinConversionRate(coinDeskResponse.getBitCoinPriceIndex());

    }

    private ResponseEntity<String> callCoinDeskApi(String coinDeskApiUrl) {
        ResponseEntity<String> responseEntity;

        try {
            responseEntity = new RestTemplate().getForEntity(coinDeskApiUrl, String.class);

        } catch (ResourceAccessException exception) {
            logger.error(ResponseCode.A3.getMessage());
            throw new ApiException(ResponseCode.A3);
        }

        return responseEntity;
    }
}
