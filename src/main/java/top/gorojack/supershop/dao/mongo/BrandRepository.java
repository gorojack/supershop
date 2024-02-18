package top.gorojack.supershop.dao.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.gorojack.supershop.pojo.Brand;

import java.util.List;

@Repository
public interface BrandRepository extends MongoRepository<Brand, ObjectId> {

    Brand findBrandByBrandSn(String brandSn);

    Brand findBrandByBrandId(String brandId);

    Page<Brand> findBrandsByNameLikeIgnoreCase(Pageable pageable, String name);
}
