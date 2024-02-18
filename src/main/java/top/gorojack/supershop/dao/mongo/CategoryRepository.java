package top.gorojack.supershop.dao.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.gorojack.supershop.pojo.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, ObjectId> {

    List<Category> findCategoriesByStCategoryIdAndNdCategoryId(Integer stCategoryId, Integer ndCategoryId);

    List<Category> findCategoriesByStCategoryId(Integer stCategoryId);
}
