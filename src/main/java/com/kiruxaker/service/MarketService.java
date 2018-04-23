package com.kiruxaker.service;

import java.math.BigDecimal;
import java.util.List;

public interface MarketService {

    BigDecimal getLast(String name);

    List<String> getAllMarketNames();

}
