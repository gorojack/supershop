package top.gorojack.supershop.dao.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.gorojack.supershop.pojo.PreviewImage;

import java.util.List;

@Repository
public interface PreviewImageRepository extends MongoRepository<PreviewImage, ObjectId> {

    List<PreviewImage> findPreviewImagesByProductId(String productId);
}
