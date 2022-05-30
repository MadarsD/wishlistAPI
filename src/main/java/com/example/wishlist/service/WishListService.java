package com.example.wishlist.service;

import com.example.wishlist.models.Wish;
import com.example.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepository wishlistRepository;


    public Wish addNewWish(Wish wish) {

        Optional<Wish> existingWish = wishlistRepository
                .findByWish(
                        wish.getWish());

        if (existingWish.isPresent()) {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED);
        }

        return wishlistRepository.save(wish);
    }

    public void updateWish(Long id, String updatedWish) {
        if (!wishlistRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        wishlistRepository.updateWishById(id, updatedWish);

    }

    public void deleteWish(Long id) {
        if (!wishlistRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        wishlistRepository.deleteById(id);
    }

    public Wish getWish(Long id) {
        return wishlistRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Wish> getWishList() {
        return wishlistRepository.findAll();
    }
}
