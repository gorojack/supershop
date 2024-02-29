package top.gorojack.supershop.dao.maria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.gorojack.supershop.pojo.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findOrdersByUidOrderByCreateTimeDescOrderSnDesc(Pageable pageable, Long uid);

    Page<Order> findOrdersByUidAndStatusOrderByCreateTimeDescOrderSnDesc(Pageable pageable, Long uid, Integer status);

    Integer countByUidAndStatus(Long uid, Integer status);
}
