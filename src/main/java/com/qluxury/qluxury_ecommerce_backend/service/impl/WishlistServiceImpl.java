package com.qluxury.qluxury_ecommerce_backend.service.impl;

import com.qluxury.qluxury_ecommerce_backend.modal.Product;
import com.qluxury.qluxury_ecommerce_backend.modal.User;
import com.qluxury.qluxury_ecommerce_backend.modal.Wishlist;
import com.qluxury.qluxury_ecommerce_backend.repository.WishlistRepository;
import com.qluxury.qluxury_ecommerce_backend.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;

    @Override
    public Wishlist createWishlist(User user) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishlistByUserId(User user) {
        Wishlist wishlist = wishlistRepository.findByUserId(user.getId());
        if(wishlist==null){
            wishlist=createWishlist(user);
        }
        return wishlist;
    }

    @Override
    public Wishlist addProductToWishlist(User user, Product product) {
        Wishlist wishlist = getWishlistByUserId(user);

        if(wishlist.getProducts().contains(product)){
            wishlist.getProducts().remove(product);
        }else wishlist.getProducts().add(product);

        return wishlistRepository.save(wishlist);
    }
}
