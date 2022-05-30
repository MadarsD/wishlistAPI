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

    @PutMapping("/update/{id}/{updatedWish}")
    public void updateWish(@PathVariable("id") Long id, @PathVariable("updatedWish") String updatedWish) {
        wishListService.updateWish(id, updatedWish);
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