package com.example.wishlist.repository;

import com.example.wishlist.models.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface WishListRepository extends JpaRepository<Wish, Long> {

    Optional<Wish> findByWish(String wish);

    @Modifying
    @Transactional
    @Query("update Wish w set w.wish = :updatedWish where w.id = :id")
    void updateWishById(
            @Param("id") Long id,
            @Param("updatedWish") String updatedWish
    );
}
