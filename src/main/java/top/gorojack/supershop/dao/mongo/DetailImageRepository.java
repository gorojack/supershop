package top.gorojack.supershop.dao.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.gorojack.supershop.pojo.DetailImage;

import java.util.List;

@Repository
public interface DetailImageRepository extends MongoRepository<DetailImage, ObjectId> {

    List<DetailImage> findDetailImagesByProductId(String productId);
}
