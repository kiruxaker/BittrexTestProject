package com.kiruxaker.service;

import de.elbatya.cryptocoins.bittrexclient.BittrexClient;
import de.elbatya.cryptocoins.bittrexclient.config.ApiCredentials;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class MarketServiceImpl implements MarketService {

    ApiCredentials credentials = new ApiCredentials(
            "f5b4ef7e099b4070909f176badb05929",
            "d9e2ae5fd93c474ea3fe5ba393148b18"
    );

    BittrexClient client = new BittrexClient(credentials);


    @Override
    public BigDecimal getLast(String name) {
        return client
                .getPublicApi()
                .getTicker(name)
                .getResult()
                .getLast();
    }

    @Override
    public List<String> getAllMarketNames() {
        List<String> marketNames = new ArrayList<>();
        client.getPublicApi().getMarkets().getResult().stream().forEach(market -> marketNames.add(market.getMarketName()));

        return marketNames;
    }

}
