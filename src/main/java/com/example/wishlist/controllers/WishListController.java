package com.example.wishlist.controllers;

import com.example.wishlist.models.Wish;
import com.example.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class WishListController {

    private final WishListService wishListService;

    @PostMapping("/add")
    public Wish addNewWish(@RequestBody Wish wish) {
        return wishListService.addNewWish(wish);
    }

    @PatchMapping("/update")
    public void updateWish(@RequestBody Wish wish) {
        wishListService.updateWish(wish);
    }

    @DeleteMapping("delete/{id}")
    public void deleteWish(@PathVariable("id") Long id) {
        wishListService.deleteWish(id);
    }

    @GetMapping("/get/{id}")
    public Wish getWish(@PathVariable("id") Long id) {
        return wishListService.getWish(id);
    }

    @GetMapping("/all")
    public List<Wish> getWishList() {
        return wishListService.getWishList();
    }
}
