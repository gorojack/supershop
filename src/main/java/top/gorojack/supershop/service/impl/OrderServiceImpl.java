package top.gorojack.supershop.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.gorojack.supershop.dao.maria.AddressRepository;
import top.gorojack.supershop.dao.maria.CartRepository;
import top.gorojack.supershop.dao.maria.OrderRepository;
import top.gorojack.supershop.dao.mongo.CityRepository;
import top.gorojack.supershop.dao.mongo.ProductRepository;
import top.gorojack.supershop.dao.mongo.SkuRepository;
import top.gorojack.supershop.handler.UserInfoThreadHolder;
import top.gorojack.supershop.pojo.*;
import top.gorojack.supershop.pojo.dto.AddressDto;
import top.gorojack.supershop.pojo.dto.CartDto;
import top.gorojack.supershop.pojo.dto.OrderConfirmDto;
import top.gorojack.supershop.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderRepository orderRepository;
    @Resource
    private CartRepository cartRepository;
    @Resource
    private SkuRepository skuRepository;
    @Resource
    private AddressRepository addressRepository;
    @Resource
    private ProductRepository productRepository;
    @Resource
    private CityRepository cityRepository;

    @Override
    public OrderConfirmDto confirmOrder(List<Long> cartIds) {
        OrderConfirmDto result = new OrderConfirmDto();
        User user = UserInfoThreadHolder.getCurrentUser();
        Address defaultAddress = addressRepository.findAddressByUidAndIsDefault(user.getUid(), 1);
        AddressDto addressDto = new AddressDto(defaultAddress);
        addressDto.setFullName(getFullCity(defaultAddress.getCityCode()));
        List<CartDto> cartDtoList = new ArrayList<>();
        cartIds.forEach(item -> {
            Optional<Cart> cart = cartRepository.findById(item);
            if (cart.isEmpty()) return;
            CartDto cartDto = new CartDto(cart.get());
            String skuId = cart.get().getSkuId();
            Sku sku = skuRepository.findSkuBySkuId(skuId);
            if (null != sku) {
                Product product = productRepository.findProductByProductId(sku.getProductId());
                cartDto.setProduct(product);
            }
            cartDtoList.add(cartDto);
        });
        Map<String, List<CartDto>> map = new HashMap<>();
        cartDtoList.forEach(item -> {
            Product product = item.getProduct();
            if (null != product) {
                String key = product.getBrandId() + ";" + product.getBrandShowName();
                if (map.containsKey(key)) {
                    map.get(key).add(item);
                } else {
                    List<CartDto> _cartDtoList = new ArrayList<>();
                    _cartDtoList.add(item);
                    map.put(key, _cartDtoList);
                }
            }
        });

        result.setAddress(addressDto);
        result.setBrands(map);
        return result;
    }

    @Override
    public Integer createOrder(List<Long> cartIds) {
        int result = 0;
        User user = UserInfoThreadHolder.getCurrentUser();
        for (Long item : cartIds) {
            Optional<Cart> cart = cartRepository.findById(item);
            if (cart.isEmpty()) continue;
            Sku sku = skuRepository.findSkuBySkuId(cart.get().getSkuId());
            if (null == sku) continue;
            Long uid = user.getUid();
            String skuId = cart.get().getSkuId();
            String orderSnPrefix = UUID.randomUUID().toString();
            String orderSn = orderSnPrefix.substring(0, orderSnPrefix.indexOf("-")) + user.getUid() + System.currentTimeMillis();
            BigDecimal totalPrice = BigDecimal.valueOf(Double.parseDouble(sku.getSalePrice()) * cart.get().getNumber());
            Order order = new Order();
            order.setUid(uid);
            order.setSkuId(skuId);
            order.setOrderSn(orderSn);
            order.setCreateTime(LocalDateTime.now());
            order.setTotalPrice(totalPrice);
            orderRepository.save(order);
            cart.get().setStatus(2);
            cartRepository.save(cart.get());
            result++;
        }
        return result;
    }

    private String getFullCity(Integer code) {
        City city = cityRepository.findCityByCode(code);
        String parentCityName = "";
        if (0 != city.getParentCode()) {
            parentCityName = getFullCity(city.getParentCode());
        }
        if (parentCityName.isEmpty()) return city.getName();
        return parentCityName + "/" + city.getName();
    }
}
