package top.gorojack.supershop.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import top.gorojack.supershop.pojo.dto.OrderDto;
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
    public Integer createOrder(OrderConfirmDto orderConfirmDto) {
        Integer result = 0;
        User user = UserInfoThreadHolder.getCurrentUser();
        Long uid = user.getUid();
        AddressDto address = orderConfirmDto.getAddress();
        Map<String, List<CartDto>> brands = orderConfirmDto.getBrands();
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        double totalPriceD = 0.0;
        for (Map.Entry<String, List<CartDto>> entry : brands.entrySet()) {
            List<CartDto> cartItem = entry.getValue();
            String orderSnPrefix = UUID.randomUUID().toString();
            String orderSn = orderSnPrefix.substring(0, orderSnPrefix.indexOf("-")) + user.getUid() + System.currentTimeMillis();
            double brandTotalPrice = 0.0;
            for (CartDto item : cartItem) {
                String skuId = item.getSkuId();
                Sku sku = skuRepository.findSkuBySkuId(skuId);
                String salePrice = sku.getSalePrice();
                double price = Double.parseDouble(salePrice) * item.getNumber();
                brandTotalPrice *= price;
                totalPriceD += brandTotalPrice;
                Order order = new Order();
                order.setUid(uid);
                order.setSkuId(skuId);
                order.setOrderSn(orderSn);
                order.setNumber(item.getNumber());
                order.setAddressId(address.getId());
                order.setCreateTime(LocalDateTime.now());
                order.setTotalPrice(price);
                orderRepository.save(order);
                Cart cart = new Cart();
                BeanUtils.copyProperties(item, cart);
                cart.setStatus(2);
                cartRepository.save(cart);
                result++;
            }
        }
        return result;
    }

    @Override
    public Map<String, List<OrderDto>> list(Integer type, Integer page, Integer pageSize) {
        User user = UserInfoThreadHolder.getCurrentUser();
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Page<Order> orderPage;
        if (type == 0) {
            orderPage = orderRepository.findOrdersByUidOrderByCreateTimeDescOrderSnDesc(pageRequest, user.getUid());
        } else {
            orderPage = orderRepository.findOrdersByUidAndStatusOrderByCreateTimeDescOrderSnDesc(pageRequest, user.getUid(), type);
        }
        List<Order> orderList = orderPage.getContent();
        Map<String, List<OrderDto>> result = new LinkedHashMap<>();
        orderList.forEach(item -> {
            OrderDto dto = new OrderDto(item);
            Product product = productRepository.findProductBySkuId(item.getSkuId());
            dto.setProduct(product);
            if (null == product) return;
            String key = product.getBrandId() + ";" + product.getBrandShowName() + ";" + dto.getOrderSn();
            if (result.containsKey(key)) {
                result.get(key).add(dto);
            } else {
                List<OrderDto> orderDtoList = new ArrayList<>();
                orderDtoList.add(dto);
                result.put(key, orderDtoList);
            }
        });
        return result;
    }

    @Override
    public Map<String, Integer> getUserStatus() {
        Map<String, Integer> result = new HashMap<>();
        User user = UserInfoThreadHolder.getCurrentUser();
        if (null == user) return result;
        Integer all = orderRepository.countByUidAndStatus(user.getUid(), 0);
        Integer unPaid = orderRepository.countByUidAndStatus(user.getUid(), 1);
        Integer waitSend = orderRepository.countByUidAndStatus(user.getUid(), 2);
        Integer waitSign = orderRepository.countByUidAndStatus(user.getUid(), 3);
        result.put("all", all);
        result.put("unPaid", unPaid);
        result.put("waitSend", waitSend);
        result.put("waitSign", waitSign);
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
