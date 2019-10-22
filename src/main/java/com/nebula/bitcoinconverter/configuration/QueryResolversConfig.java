package com.nebula.bitcoinconverter.configuration;

import com.nebula.bitcoinconverter.queryResolvers.CalculatePriceQueryResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryResolversConfig {

    @Bean
    public CalculatePriceQueryResolver calculatePriceQueryResolver(){
        return new CalculatePriceQueryResolver();
    }
}
