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
@Document("prop")
public class Prop {
    @Id
    @Column(name = "_id")
    private String id;
    private String productId;
    private List<Props> props;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Props {
        private String name;
        private String value;
    }
}
