package com.nebula.bitcoinconverter.configuration;

import com.nebula.bitcoinconverter.queryResolvers.CalculatePriceQueryResolver;
import com.nebula.bitcoinconverter.services.BitCoinPriceFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryResolversConfig {

    @Bean
    public CalculatePriceQueryResolver calculatePriceQueryResolver(@Autowired BitCoinPriceFetcher bitCoinPriceFetcher){
        return new CalculatePriceQueryResolver(bitCoinPriceFetcher);
    }
}
