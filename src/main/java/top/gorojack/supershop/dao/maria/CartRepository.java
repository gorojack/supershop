package top.gorojack.supershop.dao.maria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.gorojack.supershop.pojo.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findCartByUidAndSkuId(Long uid, String skuId);
}
