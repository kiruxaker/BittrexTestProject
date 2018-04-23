package com.kiruxaker.dao;

import com.kiruxaker.exception.BittrexException;
import com.kiruxaker.service.MarketServiceImpl;
import de.elbatya.cryptocoins.bittrexclient.BittrexClient;
import de.elbatya.cryptocoins.bittrexclient.config.ApiCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MarketDaoImpl implements MarketDao {

    @Autowired
    MarketServiceImpl service;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public MarketDaoImpl() {
    }

    public MarketServiceImpl getService() {
        return service;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setService(MarketServiceImpl service) {
        this.service = service;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void changeLast(String marketName) {
        boolean marketNameExists = service.getAllMarketNames().stream()
                .anyMatch(s -> marketName.equals(marketName));

        if (marketNameExists == true) {
            jdbcTemplate.update("UPDATE market_info SET last = " + service.getLast(marketName) + " WHERE name = " + marketName);
        } else try {
            throw new BittrexException("Such name doesn't exists...");
        } catch (BittrexException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addEverything() {
        for (String marketName : service.getAllMarketNames()) {
            jdbcTemplate.update("INSERT INTO market_info VALUES (?, ?)", marketName, service.getLast(marketName));
        }
    }
}
