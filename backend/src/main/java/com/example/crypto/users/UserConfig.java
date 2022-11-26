package com.example.crypto.users;


import com.example.crypto.users.listTypes.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            if(repository.findAll().stream().count() == 0) {
                List<User> users = new ArrayList<User>();
                List<FavfolioItem> favfolio = new ArrayList<FavfolioItem>();
                List<PortfolioItem> portfolio = new ArrayList<PortfolioItem>();
                List<WalletItem> wallet = new ArrayList<WalletItem>();
                List<String> subs = new ArrayList<String>();
                List<UserActionLogItem> actionsloglist = new ArrayList<UserActionLogItem>();
                List<StockFavfolioItem> stockfavfolio = new ArrayList<StockFavfolioItem>();
                List<StockPortfolioItem> stockportfolio = new ArrayList<StockPortfolioItem>();
                List<StockWalletItem> stockwallet = new ArrayList<StockWalletItem>();
                List<FavfolioItem> favfolioEmpty = new ArrayList<FavfolioItem>();
                List<PortfolioItem> portfolioEmpty = new ArrayList<PortfolioItem>();
                List<WalletItem> walletEmpty = new ArrayList<WalletItem>();
                List<StockFavfolioItem> stockfavfolioEmpty = new ArrayList<StockFavfolioItem>();
                List<StockPortfolioItem> stockportfolioEmpty = new ArrayList<StockPortfolioItem>();
                List<StockWalletItem> stockwalletEmpty = new ArrayList<StockWalletItem>();
                favfolio.add(new FavfolioItem( "solana", 0.0));
                favfolio.add(new FavfolioItem( "tether", 0.0));
                wallet.add(new WalletItem("tether", 10000.0));
                stockwallet.add(new StockWalletItem("USD", 10000.0));
                walletEmpty.add(new WalletItem("tether", 10000.0));
                stockwalletEmpty.add(new StockWalletItem("USD", 10000.0));
                portfolio.add(new PortfolioItem("bitcoin", 1.0, 29974.0));
                portfolio.add(new PortfolioItem("ethereum", 0.5,1434.14));
                portfolio.add(new PortfolioItem("binancecoin", 13.0,3904.0));


                List<UserActionLogItem> actionsloglistSubUser = new ArrayList<UserActionLogItem>();
                actionsloglistSubUser.add(new UserActionLogItem("favorite", "subscriptiontest@test.com","2022-11-08 11:12:05", 1.0,0.0, "bitcoin", ""));
                actionsloglistSubUser.add(new UserActionLogItem("favorite", "subscriptiontest@test.com","2022-11-08 11:13:05", 0.0,0.0, "bitcoin", ""));
                User user = new User("eVF0TPi21idLbR75enY0rNTjlVk1", "tesztuser@test.com",true, favfolio, portfolio, wallet, stockfavfolio, stockportfolio, stockwallet, actionsloglist, subs);
                users.add(new User("CvCslkZ5EnQvtgzMbXLtc90TJ6J2", "koszti.dominik@gmail.com",true, favfolioEmpty, portfolioEmpty, walletEmpty, stockfavfolioEmpty, stockportfolioEmpty, stockwalletEmpty, actionsloglist, subs));
                User subsUser = new User("ox7Lx2kJzvMhlo6jPxDpOTWmjPA3", "subscriptiontest@test.com",true, favfolio, portfolio, wallet, stockfavfolio, stockportfolio, stockwallet, actionsloglistSubUser, subs);
                users.add(user);
                users.add(subsUser);

                repository.saveAll(users);
            }
        };
    }
}