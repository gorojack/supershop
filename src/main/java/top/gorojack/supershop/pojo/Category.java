package top.gorojack.supershop.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("category")
public class Category implements Serializable {
    @Id
    @Column(name = "_id")
    private String id;
    private Integer categoryId;
    private Integer stCategoryId;
    private Integer ndCategoryId;
    private String productId;
}
