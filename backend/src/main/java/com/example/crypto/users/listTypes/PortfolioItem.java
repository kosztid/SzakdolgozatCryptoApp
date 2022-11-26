package com.example.crypto.users.listTypes;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PortfolioItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String coinid;
    private Double count;
    private Double buytotal;

    public PortfolioItem() {
    }

    public PortfolioItem(Long id, String coinid, Double count, Double buytotal) {
        this.id = id;
        this.coinid = coinid;
        this.count = count;
        this.buytotal = buytotal;
    }

    public PortfolioItem(String coinid, Double count, Double buytotal) {
        this.coinid = coinid;
        this.count = count;
        this.buytotal = buytotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoinid() {
        return coinid;
    }

    public void setCoinid(String coinid) {
        this.coinid = coinid;
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
        return "portfolioItem{" +
                "id=" + id +
                ", coinid=" + coinid +
                ", count=" + count +
                ", buytotal=" + buytotal +
                '}';
    }
}
