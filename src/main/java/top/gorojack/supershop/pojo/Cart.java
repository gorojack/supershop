package top.gorojack.supershop.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@SQLDelete(sql="update cart set status = 1 where id = ?")
@SQLRestriction("status = 0")
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uid;
    private String skuId;
    private LocalDateTime createTime;
    private Integer number;
    private Integer status;
}
