package top.gorojack.supershop.service;

import top.gorojack.supershop.pojo.Cart;
import top.gorojack.supershop.pojo.User;

public interface CartService {

    Cart add(User user, Cart cart);
}
