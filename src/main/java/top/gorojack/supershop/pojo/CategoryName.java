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
@Document("categoryName")
public class CategoryName implements Serializable {
    @Id
    @Column(name = "_id")
    private String id;
    private Integer categoryId;
    private String categoryTitle;
    private Integer stCategoryId;
    private Integer ndCategoryId;
}
