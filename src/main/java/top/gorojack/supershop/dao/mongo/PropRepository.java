package top.gorojack.supershop.dao.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.gorojack.supershop.pojo.Prop;

@Repository
public interface PropRepository extends MongoRepository<Prop, ObjectId> {

    Prop findPropsByProductId(String productId);
}
