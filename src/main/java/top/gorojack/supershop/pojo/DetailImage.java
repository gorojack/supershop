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
@Document("detailImage")
public class DetailImage {
    @Id
    @Column(name = "_id")
    private String id;
    private String imageUrl;
    private Integer imageIndex;
    private String productId;
}
