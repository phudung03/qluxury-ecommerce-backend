package com.qluxury.qluxury_ecommerce_backend.service.impl;

import com.qluxury.qluxury_ecommerce_backend.modal.Cart;
import com.qluxury.qluxury_ecommerce_backend.modal.CartItem;
import com.qluxury.qluxury_ecommerce_backend.modal.Product;
import com.qluxury.qluxury_ecommerce_backend.modal.User;
import com.qluxury.qluxury_ecommerce_backend.repository.CartItemRepository;
import com.qluxury.qluxury_ecommerce_backend.repository.CartRepository;
import com.qluxury.qluxury_ecommerce_backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;


    @Override
    public CartItem addCartItem(User user, Product product, String size, int quantity) {
        Cart cart = findUserCart(user);

        CartItem isPresent=cartItemRepository.findByCartAndProductAndSize(cart, product,size);

        if(isPresent==null){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(user.getId());
            cartItem.setSize(size);

            int totalPrice=quantity* product.getSellingPrice();
            cartItem.setSellingPrice(totalPrice);
            cartItem.setMrpPrice(quantity*product.getMrpPrice());

            cart.getCartItems().add(cartItem);
            cartItem.setCart(cart);

            return cartItemRepository.save(cartItem);
        }
        return isPresent;
    }

    @Override
    public Cart findUserCart(User user) {
        Cart cart=cartRepository.findByUserId(user.getId());

        int totalPrice=0;
        int totalDiscountedPrice=0;
        int totalItem=0;

        for(CartItem cartItem: cart.getCartItems()){
            totalPrice+=cartItem.getMrpPrice();
            totalDiscountedPrice+=cartItem.getSellingPrice();
            totalItem+=cartItem.getQuantity();
        }

        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalItems(totalItem);
        cart.setTotalSellingPrice(totalDiscountedPrice);
        cart.setDiscount(caculateDiscountPercentage(totalPrice,totalDiscountedPrice));
        cart.setTotalItems(totalItem);
        return cart;
    }

    private int caculateDiscountPercentage(int mrpPrice, int sellingPrice) {
        if(mrpPrice<=0){
            return 0;
        }
        double discount=mrpPrice-sellingPrice;
        double discountPercentage=(discount/mrpPrice)*100;
        return (int)discountPercentage;
    }
}
