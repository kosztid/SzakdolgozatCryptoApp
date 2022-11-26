package com.example.crypto.users;


import com.example.crypto.users.listTypes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    public void addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("user exists");
        }
        userRepository.save(user);
    }

    private String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    public Optional<User> getUser(String id) {
        return userRepository.findById(id);
    }

    public Optional<List<FavfolioItem>> getFavsList(String id) {
        return userRepository.findFavsList(id);
    }
    public Optional<List<PortfolioItem>> getPortfolioList(String id) {
        return userRepository.findPortfolioList(id);
    }
    public Optional<List<WalletItem>> getWalletForId(String id) {
        return userRepository.findWalletList(id);
    }

    public Optional<List<StockFavfolioItem>> getStockFavsList(String id) {
        return userRepository.findStockFavsList(id);
    }
    public Optional<List<StockPortfolioItem>> getStockPortfolioList(String id) {
        return userRepository.findStockPortfolioList(id);
    }
    public Optional<List<StockWalletItem>> getStockWalletForId(String id) {
        return userRepository.findStockWalletList(id);
    }

    @Transactional
    public void updateFav(String userID, String favID) {
        User user = userRepository.findById(userID).orElseThrow(() -> new IllegalStateException("no user"));

        List<FavfolioItem> favs = user.getFavfolio();
        double amount = 0.0;
        if (favs.stream().anyMatch(f ->favID.equals(f.getCoinid()))) {
            favs.removeIf(f -> Objects.equals(f.getCoinid(), favID));
            System.out.println("removed");
        } else {
            favs.add(new FavfolioItem(favID, 0.0));
            amount = 1.0;
            System.out.println("added");
        }
        UserActionLogItem logItem = new UserActionLogItem("favorite",user.getEmail(),getDate(),amount,0.0,favID,"");
        List<UserActionLogItem> logs = user.getUserLogs();
        logs.add(logItem);

        user.setUserLogs(logs);
        user.setFavfolio(favs);
    }
    @Transactional
    public void updatePortfolio(String id, String coinid, Double count, Double buytotal) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("no user"));
        List<PortfolioItem> list = user.getPortfolio();
        double amount = 0.0;
        if (list.stream().anyMatch(f ->coinid.equals(f.getCoinid()))) {
            if (count == 0) {
                list.removeIf(item -> Objects.equals(item.getCoinid(), coinid));
            } else {
                PortfolioItem item = user.getPortfolioItem(coinid).orElseThrow(() -> new IllegalStateException("no item"));
                amount = item.getCount();
                item.setCount(count);
                item.setBuytotal(buytotal);
            }
        } else {
            list.add(new PortfolioItem(coinid, count, buytotal));
        }

        UserActionLogItem logItem = new UserActionLogItem("portfolio",user.getEmail(),getDate(),count,amount,coinid,"");
        List<UserActionLogItem> logs = user.getUserLogs();
        logs.add(logItem);

        user.setPortfolio(list);
    }

    @Transactional
    public void updateWallet(String id, String toSellId, String toBuyId, Double sellAmount, Double buyAmount) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("no user"));
        List<WalletItem> list = user.getWallet();

        WalletItem itemToSell = user.getWalletItem(toSellId).orElseThrow(() -> new IllegalStateException("no item"));
        if (((itemToSell.getCount() - sellAmount) < 0.0000001) || (itemToSell.getCount() == sellAmount) ) {
            list.removeIf(item -> Objects.equals(item.getCoinid(), toSellId));
        } else {
            itemToSell.setCount(itemToSell.getCount() - sellAmount);
        }

        Optional<WalletItem> itemToBuy = user.getWalletItem(toBuyId);
        if (itemToBuy.isPresent()) {
        WalletItem variable;
        variable = itemToBuy.get();
            variable.setCount(variable.getCount() + buyAmount);
        } else {
            list.add(new WalletItem(toBuyId, buyAmount));
        }

        UserActionLogItem logItem = new UserActionLogItem("wallet",user.getEmail(),getDate(),sellAmount,buyAmount,toSellId,toBuyId);
        List<UserActionLogItem> logs = user.getUserLogs();
        logs.add(logItem);
        user.setWallet(list);
    }

    @Transactional
    public void updateStockFav(String userID, String favID) {
        User user = userRepository.findById(userID).orElseThrow(() -> new IllegalStateException("no user"));

        List<StockFavfolioItem> favs = user.getStockfavfolio();
        double amount = 0.0;
        if (favs.stream().anyMatch(f ->favID.equals(f.getStockSymbol()))) {
            favs.removeIf(f -> Objects.equals(f.getStockSymbol(), favID));
            System.out.println("removed");
        } else {
            favs.add(new StockFavfolioItem(favID, 0.0));
            amount = 1.0;
            System.out.println("added");
        }
        UserActionLogItem logItem = new UserActionLogItem("favorite",user.getEmail(),getDate(),amount,0.0,favID,"");
        List<UserActionLogItem> logs = user.getUserLogs();
        logs.add(logItem);

        user.setStockfavfolio(favs);
    }
    @Transactional
    public void updateStockPortfolio(String id, String symbol, Double count, Double buytotal) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("no user"));
        List<StockPortfolioItem> list = user.getStockportfolio();
        double amount = 0.0;
        if (list.stream().anyMatch(f ->symbol.equals(f.getStockSymbol()))) {
            if (count == 0) {
                list.removeIf(item -> Objects.equals(item.getStockSymbol(), symbol));
            } else {
                StockPortfolioItem item = user.getStockPortfolioItem(symbol).orElseThrow(() -> new IllegalStateException("no item"));
                amount = item.getCount();
                item.setCount(count);
                item.setBuytotal(buytotal);
            }
        } else {
            list.add(new StockPortfolioItem(symbol, count, buytotal));
        }
        UserActionLogItem logItem = new UserActionLogItem("portfolio",user.getEmail(),getDate(),count,amount,symbol,"");
        List<UserActionLogItem> logs = user.getUserLogs();
        logs.add(logItem);

        user.setStockportfolio(list);
    }

    @Transactional
    public void updateStockWallet(String id, String toSellId, String toBuyId, Double sellAmount, Double buyAmount) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("no user"));
        List<StockWalletItem> list = user.getStockwallet();

        StockWalletItem itemToSell = user.getStockWalletItem(toSellId).orElseThrow(() -> new IllegalStateException("no item"));
        if ((itemToSell.getCount() - sellAmount) < 0.000001) {
            list.removeIf(item -> Objects.equals(item.getStockSymbol(), toSellId));
        } else {
            itemToSell.setCount(itemToSell.getCount() - sellAmount);
        }

        Optional<StockWalletItem> itemToBuy = user.getStockWalletItem(toBuyId);
        if (itemToBuy.isPresent()) {
            StockWalletItem variable;
            variable = itemToBuy.get();
            variable.setCount(variable.getCount() + buyAmount);
        } else {
            list.add(new StockWalletItem(toBuyId, buyAmount));
        }

        UserActionLogItem logItem = new UserActionLogItem("wallet",user.getEmail(),getDate(),sellAmount,buyAmount,toSellId,toBuyId);
        List<UserActionLogItem> logs = user.getUserLogs();
        logs.add(logItem);

        user.setStockwallet(list);
    }

    @Transactional
    public void addNewUserWithId(String id, String email) {
        List<FavfolioItem> favfolio = new ArrayList<FavfolioItem>();
        List<String> subs = new ArrayList<String>();
        List<UserActionLogItem> actionsloglist = new ArrayList<UserActionLogItem>();
        List<PortfolioItem> portfolio = new ArrayList<PortfolioItem>();
        List<WalletItem> wallet = new ArrayList<WalletItem>();
        List<StockFavfolioItem> stockfavfolio = new ArrayList<StockFavfolioItem>();
        List<StockPortfolioItem> stockportfolio = new ArrayList<StockPortfolioItem>();
        List<StockWalletItem> stockwallet = new ArrayList<StockWalletItem>();
        Optional<User> userByEmail = userRepository.findUserByEmail(email);
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("user exists");
        }
        User user = new User(id, email,false, favfolio, portfolio, wallet, stockfavfolio, stockportfolio, stockwallet, actionsloglist, subs);
        userRepository.save(user);
        initWallet(id,"tether");
        initStockWallet(id, "USD");
    }

    @Transactional
    public void initWallet(String id, String cointoBuy) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("no user"));
        List<WalletItem> list = user.getWallet();

        Optional<WalletItem> itemToBuy = user.getWalletItem(cointoBuy);

        if (itemToBuy.isPresent()) {
            WalletItem item = user.getWalletItem(cointoBuy).orElseThrow(() -> new IllegalStateException("no item"));
            item.setCount(item.getCount() + 10000);
        } else {
            list.add(new WalletItem(cointoBuy, 10000.0));
        }
        user.setWallet(list);
    }
    @Transactional
    public void initStockWallet(String id, String stocksymbol) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("no user"));
        List<StockWalletItem> list = user.getStockwallet();
        Optional<StockWalletItem> itemToBuy = user.getStockWalletItem(stocksymbol);

        if (itemToBuy.isPresent()) {
            StockWalletItem item = user.getStockWalletItem(stocksymbol).orElseThrow(() -> new IllegalStateException("no item"));
            item.setCount(item.getCount() + 10000);
        } else {
            list.add(new StockWalletItem(stocksymbol, 10000.0));
        }
        user.setStockwallet(list);
    }

    @Transactional
    public Optional<List<UserActionLogItem>> getUserActions(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("no user"));
        List<User> userList = userRepository.findAll();

        List<UserActionLogItem> actionsList = new ArrayList<UserActionLogItem>();
        List<String> subscriptions = user.getSubscriptions();

        for(User usr: userList) {
            if(subscriptions.contains(usr.getId()) && usr.isVisibility()) {
                actionsList.addAll(usr.getUserLogs());
            }
        }
        return Optional.of(actionsList);
    }

    @Transactional
    public void subscribe(String id, String subbedId) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("no user"));
        List<String> subscriptions = user.getSubscriptions();
        if(subscriptions.contains(subbedId)) {
            subscriptions.remove(subbedId);
        } else {
            subscriptions.add(subbedId);
        }
        user.setSubscriptions(subscriptions);
    }

    @Transactional
    public void updateVisibility(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("no user"));
        user.setVisibility(!user.isVisibility());
    }
}
