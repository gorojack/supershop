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
@Document("city")
public class City {

    @Id
    @Column(name = "_id")
    private String id;
    private Integer code;
    private String name;
    private String pinyin;
    private Integer zipCode;
    private Integer parentCode;
    private Integer type;
    private String firstLetter;
}
