package com.example.wishlist.repository;

import com.example.wishlist.models.Wish;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WishListRepository extends JpaRepository<Wish, Long> {

    boolean existsByWish(String wish);

}
