package top.gorojack.supershop.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uid;
    private String orderSn;
    private String skuId;
    private Long addressId;
    private LocalDateTime createTime;
    private LocalDateTime paymentTime;
    private BigDecimal totalPrice;
    private String comment;
    private Integer status = 0;
}
