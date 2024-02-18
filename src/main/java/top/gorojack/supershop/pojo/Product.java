package top.gorojack.supershop.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("product")
public class Product implements Serializable {
    @Id
    @Column(name = "_id")
    private String id;
    private String productId;
    private String brandId;
    private String brandStoreSn;
    private String categoryId;
    private String spuId;
    private String skuId;
    private String status;
    private String title;
    private String brandShowName;
    private String smallImage;
    private String squareImage;
    private String logo;
    private Price price;
    private List<Attrs> attrs;
    private Integer flags;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Price {
        private String priceType;
        private String priceLabelType;
        private String priceLabel;
        private String salePrice;
        private String salePriceSuff;
        private String saleDiscount;
        private String marketPrice;
        private String mixPriceLabel;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Attrs {
        private String name;
        private String value;
    }
}
