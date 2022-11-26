package com.example.crypto.users.listTypes;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FavfolioItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String coinid;
    private Double count;

    public FavfolioItem() {
    }

    public FavfolioItem(Long id, String coinid, Double count) {
        this.id = id;
        this.coinid = coinid;
        this.count = count;
    }

    public FavfolioItem(String coinid, Double count) {
        this.coinid = coinid;
        this.count = count;
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

    @Override
    public String toString() {
        return "favfolioItem{" +
                "id=" + id +
                ", coinid=" + coinid +
                ", count=" + count +
                '}';
    }
}

