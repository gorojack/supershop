package top.gorojack.supershop.dao.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import top.gorojack.supershop.pojo.SaleProp;
import top.gorojack.supershop.pojo.Sku;

import java.util.List;

@Repository
public interface SalePropRepository extends MongoRepository<SaleProp, ObjectId> {

    List<SaleProp> findSalePropsByProductId(String productId);

    @Query("{'$and': [{'productId': '?0'},{'pid':'?1'},{'values.vid': '?2'}]}")
    SaleProp findSalePropBySku(String productId, String pid, String vid);
}
