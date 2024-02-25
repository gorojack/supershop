package top.gorojack.supershop.dao.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.gorojack.supershop.pojo.Sku;

import java.util.List;

@Repository
public interface SkuRepository extends MongoRepository<Sku, ObjectId> {

    List<Sku> findSkusByProductId(String productId);

    List<Sku> findSkuBySkuAndProductId(String sku, String productId);

    Sku findSkuBySkuId(String skuId);
}
