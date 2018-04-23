package com.kiruxaker.model;

import java.math.BigDecimal;

public class Market {

    public String name;
    public BigDecimal last;

    public Market(String name, BigDecimal last) {
        this.name = name;
        this.last = last;
    }

    public Market(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLast() {
        return this.last;
    }

    public void setLast(BigDecimal last) {
        this.last = last;
    }
}
