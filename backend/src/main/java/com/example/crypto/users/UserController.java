package com.example.crypto.users;


import com.example.crypto.users.actionModels.AddUserModel;
import com.example.crypto.users.actionModels.FavfolioUpdateModel;
import com.example.crypto.users.actionModels.PortfolioUpdateModel;
import com.example.crypto.users.actionModels.SwapModel;
import com.example.crypto.users.listTypes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody final AddUserModel model){
        userService.addNewUserWithId(model.id, model.email);
    }
    @GetMapping(path = "{id}")
    public Optional<User> getUser(@PathVariable("id") String id) {
        return userService.getUser(id);
    }

    @GetMapping(path = "{id}/actionsList")
    public Optional<List<UserActionLogItem>> getUserActionLogs(@PathVariable("id") String id) {
        return userService.getUserActions(id);
    }

    @GetMapping(path = "{id}/stockfavfolio")
    public Optional<List<StockFavfolioItem>> getStockFavsListForId(@PathVariable("id") String id) {
        return userService.getStockFavsList(id);
    }
    @PutMapping(path = "{id}/changeVisibility")
    public void updateVisibility(@PathVariable("id") String id) {
        userService.updateVisibility(id);
    }

    @PutMapping(path = "{id}/stockfavfolio/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStockFavs(@PathVariable("id") String id, @RequestBody final FavfolioUpdateModel model) {
        userService.updateStockFav(id, model.id);
    }
    @GetMapping(path = "{id}/stockportfolio")
    public Optional<List<StockPortfolioItem>> getStockPortfolioListForId(@PathVariable("id") String id) {
        return userService.getStockPortfolioList(id);
    }

    @PutMapping(path = "{id}/stockportfolio/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStockPortfolio(@PathVariable("id") String id,
                                @RequestBody final PortfolioUpdateModel model) {
        userService.updateStockPortfolio(id, model.id, model.count, model.buytotal);
    }

    @GetMapping(path = "{id}/stockwallet")
    public Optional<List<StockWalletItem>> getStockWalletForId(@PathVariable("id") String id) {
        return userService.getStockWalletForId(id);
    }

    @PutMapping(path = "{id}/stockwallet/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStockWallet(@PathVariable("id") String id, @RequestBody final SwapModel swapModel) {
        userService.updateStockWallet(id, swapModel.toSell, swapModel.toBuy, swapModel.sellAmount, swapModel.buyAmount);
    }


    @GetMapping(path = "{id}/favfolio")
    public Optional<List<FavfolioItem>> getFavsListForId(@PathVariable("id") String id) {
        return userService.getFavsList(id);
    }

    @PutMapping(path = "{id}/favfolio/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateFavs(@PathVariable("id") String id, @RequestBody final FavfolioUpdateModel model) {
        userService.updateFav(id, model.id);
    }
    @GetMapping(path = "{id}/portfolio")
    public Optional<List<PortfolioItem>> getPortfolioListForId(@PathVariable("id") String id) {
        return userService.getPortfolioList(id);
    }

    @PutMapping(path = "{id}/portfolio/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePortfolio(@PathVariable("id") String id,
                                @RequestBody final PortfolioUpdateModel model) {
        userService.updatePortfolio(id, model.id, model.count, model.buytotal);
    }

    @GetMapping(path = "{id}/wallet")
    public Optional<List<WalletItem>> getWalletForId(@PathVariable("id") String id) {
        return userService.getWalletForId(id);
    }

    @PutMapping(path = "{id}/wallet/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateWallet(@PathVariable("id") String id, @RequestBody final SwapModel swapModel) {
        userService.updateWallet(id, swapModel.toSell, swapModel.toBuy, swapModel.sellAmount, swapModel.buyAmount);
    }

    @PutMapping(path = "{id}/wallet/init/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void initWallet(@PathVariable("id") String id, @RequestBody final FavfolioUpdateModel model) {
        userService.initWallet(id, model.id);
    }

    @PutMapping(path = "{id}/stockwallet/init/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void initStockWallet(@PathVariable("id") String id, @RequestBody final FavfolioUpdateModel model) {
        userService.initStockWallet(id, model.id);
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @PutMapping(path = "{id}/subscribe/{subId}")
    public void subscribe(@PathVariable("id") String id, @PathVariable("subId") String subId) {
        userService.subscribe(id, subId);
    }
    /*    @PutMapping(path = "{id}/wallet/")
    public void updateWallet(@PathVariable("id") Long id,
                                @RequestParam(required = true) String cointoSell,
                                @RequestParam(required = true) String cointoBuy,
                                @RequestParam(required = true) Double sellAmount,
                                @RequestParam(required = true) Double buyAmount) {
        userService.updateWallet(id, cointoSell, cointoBuy, sellAmount, buyAmount);
    }*/
}
