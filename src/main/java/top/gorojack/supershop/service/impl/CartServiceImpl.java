package top.gorojack.supershop.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.gorojack.supershop.dao.maria.CartRepository;
import top.gorojack.supershop.dao.mongo.ProductRepository;
import top.gorojack.supershop.dao.mongo.SkuRepository;
import top.gorojack.supershop.pojo.Cart;
import top.gorojack.supershop.pojo.Product;
import top.gorojack.supershop.pojo.Sku;
import top.gorojack.supershop.pojo.User;
import top.gorojack.supershop.pojo.dto.CartDto;
import top.gorojack.supershop.service.CartService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    private CartRepository cartRepository;
    @Resource
    private SkuRepository skuRepository;
    @Resource
    private ProductRepository productRepository;

    @Override
    public Cart add(User user, Cart cart) {
        Cart dbCart = cartRepository.findCartByUidAndSkuId(user.getUid(), cart.getSkuId());
        if (null == dbCart) {
            Cart _cart = new Cart();
            _cart.setUid(user.getUid());
            _cart.setSkuId(cart.getSkuId());
            _cart.setNumber(cart.getNumber());
            _cart.setCreateTime(LocalDateTime.now());
            _cart.setStatus("0");
            return cartRepository.save(_cart);
        }
        dbCart.setNumber(dbCart.getNumber() + 1);
        return cartRepository.save(dbCart);
    }

    @Override
    public List<CartDto> findUserCart(Long uid) {
        List<Cart> cartPage = cartRepository.findCartsByUidAndStatus(uid,"0");
        List<CartDto> cartDtoList = new ArrayList<>();
        cartPage.forEach(item -> {
            CartDto cartDto = new CartDto(item);
            Sku sku = skuRepository.findSkuBySkuId(item.getSkuId());
            if (null != sku) {
                Product product = productRepository.findProductByProductId(sku.getProductId());
                cartDto.setProduct(product);
            }
            cartDtoList.add(cartDto);
        });
        return cartDtoList;
    }

    @Override
    public Cart updateNumber(Cart cart) {
        Optional<Cart> exist = cartRepository.findById(cart.getId());
        if (exist.isEmpty()) return null;
        Cart existCart = exist.get();
        existCart.setNumber(cart.getNumber());
        return cartRepository.save(existCart);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        // TODO soft delete by ids
    }
}
