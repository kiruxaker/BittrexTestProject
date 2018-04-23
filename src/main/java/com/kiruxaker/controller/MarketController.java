package com.kiruxaker.controller;

import com.kiruxaker.model.Market;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarketController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/last")
    public Market last(@RequestParam String marketName) {
        String query = "SELECT last from markets where name = " + marketName;

        return jdbcTemplate.queryForObject(query, ((resultSet, i) -> {
            return new Market(resultSet.getString(1), resultSet.getBigDecimal(2));
        }));

    }

}
