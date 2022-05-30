package wish.service;

import com.example.wishlist.models.Wish;
import com.example.wishlist.repository.WishListRepository;
import com.example.wishlist.service.WishListService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WishListServiceTest {

    @Mock
    WishListRepository wishListRepository;

    @InjectMocks
    WishListService wishListService;

    private final Wish firstWish = new Wish(1L,
            "First wish"
    );

    @DisplayName("Test for addNewWish() method")
    @Test
    public void addingNewWishIsSuccessful() {

        given(wishListRepository.save(firstWish)).willReturn(firstWish);

        Wish savedWish = wishListService.addNewWish(firstWish);

        assertThat(savedWish).isNotNull();
        assertThat(savedWish).isEqualTo(firstWish);
    }

    @DisplayName("Test for addNewWish() method to fail on identical")
    @Test
    public void addingIdenticalWishIsNotSuccessful() {

        Exception exception = null;

        Wish identicalWish = new Wish(2L,
                "First wish"
        );

        given(wishListRepository.findByWish(
                firstWish.getWish())).willReturn(Optional.of(firstWish));

        try {
            wishListService.addNewWish(identicalWish);
        } catch (ResponseStatusException e) {
            exception = e;
        }

        assert exception != null;
        Assertions.assertEquals(exception.getMessage(), "409 CONFLICT");
    }


    @DisplayName("Test for updateWish() method")
    @Test
    public void willUpdatePersonWhenPassedIdIsValid() {

        wishListRepository.save(firstWish);

        when(wishListRepository.existsById(1L)).thenReturn(true);
        doNothing().when(wishListRepository).updateWishById(1L, "First wish updated");

        wishListService.updateWish(1L, "First wish updated");

        verify(wishListRepository, times(1)).updateWishById(1L, "First wish updated");
    }

    @DisplayName("Test for deleteWish() method when exists")
    @Test
    public void deletingWishIsSuccessfulWhenWishExists() {

        Wish newWish = new Wish(2L, "newWish");
        wishListRepository.save(firstWish);
        wishListRepository.save(newWish);

        when(wishListRepository.existsById(2L)).thenReturn(true);
        doNothing().when(wishListRepository).deleteById(2L);

        wishListService.deleteWish(2L);

        Assertions.assertFalse(wishListRepository.findById(2L).isPresent());
    }

    @DisplayName("Test for deleteWish() method when does not exist")
    @Test
    public void deletingWishWillThrowErrorWhenWishNotExists() {

        wishListRepository.save(firstWish);

        when(wishListRepository.existsById(2L)).thenReturn(false);

        ResponseStatusException thrown = assertThrows(
                ResponseStatusException.class,
                () -> wishListService.deleteWish(2L));

        Assertions.assertNotNull(thrown);
        Assertions.assertTrue(Objects.requireNonNull(thrown.getMessage()).contains("404 NOT_FOUND"));
    }

    @DisplayName("Test for getWish() method")
    @Test
    public void gettingExistingWishWillReturn() {

        wishListRepository.save(firstWish);

        given(wishListRepository.findById(1L)).willReturn(Optional.of(firstWish));

        Wish wish = wishListService.getWish(1L);

        assertThat(wish).isNotNull();
        assertThat(wish.getWish()).isEqualTo("First wish");
    }

    @DisplayName("Test for getWish() method when wish not exists")
    @Test
    public void gettingNonExistingWishWillThrowError() {

        wishListRepository.save(firstWish);

        given(wishListRepository.findById(2L)).willThrow(ResponseStatusException.class);

        assertThrows(ResponseStatusException.class, () -> wishListService.getWish(2L));
    }

    @DisplayName("Test for getWishList() method")
    @Test
    public void returningAllWishesIsSuccessful() {

        Wish newWish = new Wish(2L, "newWish");
        wishListRepository.save(firstWish);
        wishListRepository.save(newWish);

        given(wishListRepository.findAll()).willReturn(List.of(firstWish, newWish));

        List<Wish> wishList = wishListService.getWishList();

        assertThat(wishList).isNotNull();
        assertThat(wishList.size()).isEqualTo(2);

    }

}
