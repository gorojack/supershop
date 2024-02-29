package top.gorojack.supershop.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import top.gorojack.supershop.pojo.Order;
import top.gorojack.supershop.pojo.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends Order {

    private Product product;

    public OrderDto(Order order) {
        BeanUtils.copyProperties(order, this);
    }
}
