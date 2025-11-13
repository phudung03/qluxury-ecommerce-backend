package com.qluxury.qluxury_ecommerce_backend.service;

import com.qluxury.qluxury_ecommerce_backend.modal.Cart;
import com.qluxury.qluxury_ecommerce_backend.modal.CartItem;
import com.qluxury.qluxury_ecommerce_backend.modal.Product;
import com.qluxury.qluxury_ecommerce_backend.modal.User;

public interface CartService {
    public CartItem addCartItem(User user, Product product, String size,int quantity);
    public Cart findUserCart(User user);
}
