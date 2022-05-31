package com.example.wishlist.repository;

import com.example.wishlist.models.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishListRepository extends JpaRepository<Wish, Long> {

    Optional<Wish> findByWish(String wish);

}
