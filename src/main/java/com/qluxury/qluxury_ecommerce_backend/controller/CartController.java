package com.qluxury.qluxury_ecommerce_backend.controller;

import com.qluxury.qluxury_ecommerce_backend.exceptions.ProductException;
import com.qluxury.qluxury_ecommerce_backend.modal.Cart;
import com.qluxury.qluxury_ecommerce_backend.modal.CartItem;
import com.qluxury.qluxury_ecommerce_backend.modal.Product;
import com.qluxury.qluxury_ecommerce_backend.modal.User;
import com.qluxury.qluxury_ecommerce_backend.request.AddItemRequest;
import com.qluxury.qluxury_ecommerce_backend.response.ApiResponse;
import com.qluxury.qluxury_ecommerce_backend.service.CartItemService;
import com.qluxury.qluxury_ecommerce_backend.service.CartService;
import com.qluxury.qluxury_ecommerce_backend.service.ProductService;
import com.qluxury.qluxury_ecommerce_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findUserCart(user);

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }
    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestHeader("Authorization")String jwt,
                                                  @RequestBody AddItemRequest req) throws ProductException, Exception {
        User user = userService.findUserByJwtToken(jwt);
        Product product = productService.findProductById(req.getProductId());
        CartItem item = cartService.addCartItem(user, product, req.getSize(), req.getQuantity());
        ApiResponse res = new ApiResponse();
        res.setMessage("Item added To cart Successfully");

        return new ResponseEntity<>(item,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItemHandler(@PathVariable Long cartItemId,
                                                             @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        cartItemService.removeCartItem(user.getId(),cartItemId);

        ApiResponse res = new ApiResponse();
        res.setMessage("Item remove from Cart");

        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }
    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemHandler(@PathVariable Long cartItemId,
                                                          @RequestBody CartItem cartItem,
                                                          @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        CartItem updatedCartItem = null;
        if(cartItem.getQuantity()>0){
            updatedCartItem=cartItemService.updateCartItem(user.getId(), cartItemId,cartItem);
        }
        return new ResponseEntity<>(updatedCartItem, HttpStatus.ACCEPTED);
    }
}
