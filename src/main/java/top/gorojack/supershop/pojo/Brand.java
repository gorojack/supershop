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
@Document("brand")
public class Brand {
    @Id
    @Column(name = "_id")
    private String id;
    private String brandSn;
    private String brandId;
    private String name;
    private String logo;
    private String slogan;
    private String brandStory;
    private Long favouriteCount;
}
