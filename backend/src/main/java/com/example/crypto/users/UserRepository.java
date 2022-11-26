package com.example.crypto.users;


import com.example.crypto.users.listTypes.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findUserByEmail(String email);

    @Query("SELECT u.favfolio FROM User u WHERE u.id = ?1")
    Optional<List<FavfolioItem>> findFavsList(String id);

    @Query("SELECT u.portfolio FROM User u WHERE u.id = ?1")
    Optional<List<PortfolioItem>> findPortfolioList(String id);

    @Query("SELECT u.wallet FROM User u WHERE u.id = ?1")
    Optional<List<WalletItem>> findWalletList(String id);

    @Query("SELECT u.stockfavfolio FROM User u WHERE u.id = ?1")
    Optional<List<StockFavfolioItem>> findStockFavsList(String id);

    @Query("SELECT u.stockportfolio FROM User u WHERE u.id = ?1")
    Optional<List<StockPortfolioItem>> findStockPortfolioList(String id);

    @Query("SELECT u.stockwallet FROM User u WHERE u.id = ?1")
    Optional<List<StockWalletItem>> findStockWalletList(String id);
}
