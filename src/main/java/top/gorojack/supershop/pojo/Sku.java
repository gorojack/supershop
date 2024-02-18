package top.gorojack.supershop.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("sku")
public class Sku {
    @Id
    @Column(name = "_id")
    private String id;
    private String sku;
    private String skuId;
    private String sizeDetailId;
    private String productId;
    private String saleMarketPrice;
    private String saleDiscount;
    private String salePrice;
}
