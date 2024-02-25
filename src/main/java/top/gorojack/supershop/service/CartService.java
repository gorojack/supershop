package top.gorojack.supershop.service;

import top.gorojack.supershop.pojo.Cart;
import top.gorojack.supershop.pojo.User;
import top.gorojack.supershop.pojo.dto.CartDto;

import java.util.List;

public interface CartService {

    Cart add(User user, Cart cart);

    List<CartDto> findUserCart(Long uid);

    Cart updateNumber(Cart cart);

    void deleteByIds(List<Integer> ids);
}
