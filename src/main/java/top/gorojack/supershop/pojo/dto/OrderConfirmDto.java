package top.gorojack.supershop.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderConfirmDto {

    private AddressDto address;
    private Map<String, List<CartDto>> brands;
}
