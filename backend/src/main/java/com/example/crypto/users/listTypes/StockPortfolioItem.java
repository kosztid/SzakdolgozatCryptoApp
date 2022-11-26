package com.example.crypto.users.listTypes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StockPortfolioItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String stockSymbol;
    private Double count;
    private Double buytotal;

    public StockPortfolioItem() {
    }

    public StockPortfolioItem(String stockSymbol, Double count, Double buytotal) {
        this.stockSymbol = stockSymbol;
        this.count = count;
        this.buytotal = buytotal;
    }

    public StockPortfolioItem(Long id, String stockSymbol, Double count, Double buytotal) {
        this.id = id;
        this.stockSymbol = stockSymbol;
        this.count = count;
        this.buytotal = buytotal;
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

    public Double getBuytotal() {
        return buytotal;
    }

    public void setBuytotal(Double buytotal) {
        this.buytotal = buytotal;
    }

    @Override
    public String toString() {
        return "StockPortfolioItem{" +
                "id=" + id +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", count=" + count +
                ", buytotal=" + buytotal +
                '}';
    }
}
