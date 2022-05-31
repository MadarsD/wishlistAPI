package com.example.wishlist.service;

import com.example.wishlist.models.Wish;
import com.example.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepository wishlistRepository;

    public Wish addNewWish(Wish wish) {
        if (wishlistRepository.existsByWish(wish.getWish())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } else {
            return wishlistRepository.save(wish);
        }
    }

    public void updateWish(Wish wish) {
        if (wishlistRepository.existsById(wish.getId())) {
            wishlistRepository.save(wish);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
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

