package top.gorojack.supershop.service;

import top.gorojack.supershop.pojo.dto.OrderConfirmDto;

import java.util.List;

public interface OrderService {

    OrderConfirmDto confirmOrder(List<Long> cartIds);

    Integer createOrder(List<Long> cartIds);
}
