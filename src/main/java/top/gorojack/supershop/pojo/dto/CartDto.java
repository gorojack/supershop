package top.gorojack.supershop.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import top.gorojack.supershop.pojo.Cart;
import top.gorojack.supershop.pojo.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto extends Cart {

    private Product product;

    public CartDto(Cart cart) {
        BeanUtils.copyProperties(cart, this);
    }
}
