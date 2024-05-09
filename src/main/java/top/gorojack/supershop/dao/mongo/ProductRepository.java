package top.gorojack.supershop.dao.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import top.gorojack.supershop.pojo.Product;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {

    Page<Product> findProductsByBrandShowNameLikeIgnoreCase(Pageable pageable, String brandName);

    Page<Product> findProductsByBrandShowNameLikeIgnoreCaseAndProductIdIn(Pageable pageable, String brandName, List<String> productIds);

    Product findProductByProductId(String productId);

    Page<Product> findProductsByProductIdIn(Pageable pageable, List<String> productIds);

    Product findProductBySkuId(String skuId);

    Page<Product> findProductsByTitleLikeIgnoreCase(Pageable pageable, String title);
}
