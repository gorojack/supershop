package top.gorojack.supershop.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uid;
    private String orderSn;
    private String skuId;
    private Integer number;
    private Long addressId;
    private LocalDateTime createTime;
    private LocalDateTime paymentTime;
    private Double totalPrice;
    private String comment;
    private Integer status = 1;
}
