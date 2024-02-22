package top.gorojack.supershop.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("saleProp")
public class SaleProp {
    @Id
    @Column(name = "_id")
    private String id;
    private String name;
    private String pid;
    private String type;
    private List<Values> values;
    private String productId;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Values {
        private String name;
        private String vid;
        private String sizeTableDetailId;
        private String productId;
    }
}
