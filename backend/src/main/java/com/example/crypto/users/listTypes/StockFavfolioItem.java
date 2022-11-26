package com.example.crypto.users.listTypes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StockFavfolioItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String stockSymbol;
    private Double count;

    public StockFavfolioItem() {
    }

    public StockFavfolioItem(String stockSymbol, Double count) {
        this.stockSymbol = stockSymbol;
        this.count = count;
    }

    public StockFavfolioItem(Long id, String stockSymbol, Double count) {
        this.id = id;
        this.stockSymbol = stockSymbol;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "StockFavfolioItem{" +
                "id=" + id +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", count=" + count +
                '}';
    }
}
