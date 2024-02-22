package top.gorojack.supershop.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.gorojack.supershop.dao.maria.CartRepository;
import top.gorojack.supershop.pojo.Cart;
import top.gorojack.supershop.pojo.User;
import top.gorojack.supershop.service.CartService;

import java.time.LocalDateTime;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    private CartRepository cartRepository;

    @Override
    public Cart add(User user, Cart cart) {
        Cart dbCart = cartRepository.findCartByUidAndSkuId(user.getUid(), cart.getSkuId());
        if (null == dbCart) {
            Cart _cart = new Cart();
            _cart.setUid(user.getUid());
            _cart.setSkuId(cart.getSkuId());
            _cart.setNumber(cart.getNumber());
            _cart.setCreateTime(LocalDateTime.now());
            return cartRepository.save(_cart);
        }
        dbCart.setNumber(dbCart.getNumber() + 1);
        return cartRepository.save(dbCart);
    }
}
