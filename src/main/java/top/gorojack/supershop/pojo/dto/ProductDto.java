package top.gorojack.supershop.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import top.gorojack.supershop.pojo.DetailImage;
import top.gorojack.supershop.pojo.PreviewImage;
import top.gorojack.supershop.pojo.Product;
import top.gorojack.supershop.pojo.Prop;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends Product {

    private List<DetailImage> detailImages;
    private List<PreviewImage> previewImages;
    private List<Prop.Props> props;

    public ProductDto(Product product) {
        BeanUtils.copyProperties(product, this);
    }
}
