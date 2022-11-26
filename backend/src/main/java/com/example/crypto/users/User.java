package com.example.crypto.users;


import com.example.crypto.users.listTypes.*;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;
    private String email;

    private boolean visibility;

    @OneToMany(targetEntity = FavfolioItem.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "uf_fk", referencedColumnName = "id")
    private List<FavfolioItem> favfolio;
    @OneToMany(targetEntity = PortfolioItem.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "up_fk", referencedColumnName = "id")
    private List<PortfolioItem> portfolio;
    @OneToMany(targetEntity = WalletItem.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "uw_fk", referencedColumnName = "id")
    private List<WalletItem> wallet;

    @OneToMany(targetEntity = StockFavfolioItem.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "suf_fk", referencedColumnName = "id")
    private List<StockFavfolioItem> stockfavfolio;

    @OneToMany(targetEntity = StockPortfolioItem.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "sup_fk", referencedColumnName = "id")
    private List<StockPortfolioItem> stockportfolio;

    @OneToMany(targetEntity = StockWalletItem.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "suw_fk", referencedColumnName = "id")
    private List<StockWalletItem> stockwallet;

    @OneToMany(targetEntity = UserActionLogItem.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "uali_fk", referencedColumnName = "id")
    private List<UserActionLogItem> userLogs;

    @ElementCollection
    private List<String> subscriptions;



    public User() {
    }

    public User(String email, boolean visibility, List<FavfolioItem> favfolio, List<PortfolioItem> portfolio, List<WalletItem> wallet, List<StockFavfolioItem> stockfavfolio, List<StockPortfolioItem> stockportfolio, List<StockWalletItem> stockwallet, List<UserActionLogItem> userLogs, List<String> subscriptions) {
        this.email = email;
        this.visibility = visibility;
        this.favfolio = favfolio;
        this.portfolio = portfolio;
        this.wallet = wallet;
        this.stockfavfolio = stockfavfolio;
        this.stockportfolio = stockportfolio;
        this.stockwallet = stockwallet;
        this.userLogs = userLogs;
        this.subscriptions = subscriptions;
    }

    public User(String id, String email, boolean visibility, List<FavfolioItem> favfolio, List<PortfolioItem> portfolio, List<WalletItem> wallet, List<StockFavfolioItem> stockfavfolio, List<StockPortfolioItem> stockportfolio, List<StockWalletItem> stockwallet, List<UserActionLogItem> userLogs, List<String> subscriptions) {
        this.id = id;
        this.email = email;
        this.visibility = visibility;
        this.favfolio = favfolio;
        this.portfolio = portfolio;
        this.wallet = wallet;
        this.stockfavfolio = stockfavfolio;
        this.stockportfolio = stockportfolio;
        this.stockwallet = stockwallet;
        this.userLogs = userLogs;
        this.subscriptions = subscriptions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public List<FavfolioItem> getFavfolio() {
        return favfolio;
    }

    public void setFavfolio(List<FavfolioItem> favfolio) {
        this.favfolio = favfolio;
    }

    public List<PortfolioItem> getPortfolio() {
        return portfolio;
    }
    public Optional<PortfolioItem> getPortfolioItem(String id) {
        return portfolio.stream().filter(o -> o.getCoinid().equals(id)).findFirst();
    }

    public void setPortfolio(List<PortfolioItem> portfolio) {
        this.portfolio = portfolio;
    }

    public List<WalletItem> getWallet() {
        return wallet;
    }
    public Optional<WalletItem> getWalletItem(String id) {
        return wallet.stream().filter(o -> o.getCoinid().equals(id)).findFirst();
    }

    public void setWallet(List<WalletItem> wallet) {
        this.wallet = wallet;
    }

    public List<StockFavfolioItem> getStockfavfolio() {
        return stockfavfolio;
    }

    public void setStockfavfolio(List<StockFavfolioItem> stockfavfolio) {
        this.stockfavfolio = stockfavfolio;
    }

    public List<StockPortfolioItem> getStockportfolio() {
        return stockportfolio;
    }
    public Optional<StockPortfolioItem> getStockPortfolioItem(String id) {
        return stockportfolio.stream().filter(o -> o.getStockSymbol().equals(id)).findFirst();
    }
    public void setStockportfolio(List<StockPortfolioItem> stockportfolio) {
        this.stockportfolio = stockportfolio;
    }

    public List<StockWalletItem> getStockwallet() {
        return stockwallet;
    }
    public Optional<StockWalletItem> getStockWalletItem(String id) {
        return stockwallet.stream().filter(o -> o.getStockSymbol().equals(id)).findFirst();
    }
    public void setStockwallet(List<StockWalletItem> stockwallet) {
        this.stockwallet = stockwallet;
    }

    public List<UserActionLogItem> getUserLogs() {
        return userLogs;
    }

    public void setUserLogs(List<UserActionLogItem> userLogs) {
        this.userLogs = userLogs;
    }

    public List<String> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<String> subsciptions) {
        this.subscriptions = subsciptions;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", visibility=" + visibility +
                ", favfolio=" + favfolio +
                ", portfolio=" + portfolio +
                ", wallet=" + wallet +
                ", stockfavfolio=" + stockfavfolio +
                ", stockportfolio=" + stockportfolio +
                ", stockwallet=" + stockwallet +
                ", userLogs=" + userLogs +
                ", subscriptions=" + subscriptions +
                '}';
    }
}

