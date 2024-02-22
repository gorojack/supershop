package top.gorojack.supershop.dao.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import top.gorojack.supershop.pojo.CategoryName;

import java.util.List;

@Repository
public interface CategoryNameRepository extends MongoRepository<CategoryName, ObjectId> {

    List<CategoryName> findCategoryNamesByStCategoryIdAndNdCategoryId(Integer stCategoryId, Integer ndCategoryId);

    CategoryName findCategoryNameByCategoryId(Integer categoryId);

    @Query("{'$and': [{'ndCategoryId': 0},{'stCategoryId':  {$ne: 0}}]}")
    List<CategoryName> findAllNdCategory();
}
