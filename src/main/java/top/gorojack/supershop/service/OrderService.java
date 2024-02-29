package top.gorojack.supershop.service;

import top.gorojack.supershop.pojo.dto.OrderConfirmDto;
import top.gorojack.supershop.pojo.dto.OrderDto;

import java.util.List;
import java.util.Map;

public interface OrderService {

    OrderConfirmDto confirmOrder(List<Long> cartIds);

    Integer createOrder(OrderConfirmDto orderConfirmDto);

    Map<String, List<OrderDto>> list(Integer type, Integer page, Integer pageSize);

    Map<String, Integer> getUserStatus();
}
