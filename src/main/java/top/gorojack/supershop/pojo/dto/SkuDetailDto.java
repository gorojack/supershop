package top.gorojack.supershop.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import top.gorojack.supershop.pojo.SaleProp;
import top.gorojack.supershop.pojo.Sku;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkuDetailDto extends Sku {

    private List<SaleProp> saleProps;

    public SkuDetailDto(Sku sku) {
        BeanUtils.copyProperties(sku, this);
    }
}
