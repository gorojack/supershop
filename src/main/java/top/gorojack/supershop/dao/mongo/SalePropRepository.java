package top.gorojack.supershop.dao.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.gorojack.supershop.pojo.SaleProp;

@Repository
public interface SalePropRepository extends MongoRepository<SaleProp, ObjectId> {
}
