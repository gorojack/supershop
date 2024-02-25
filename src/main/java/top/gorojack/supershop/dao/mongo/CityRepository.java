package top.gorojack.supershop.dao.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.gorojack.supershop.pojo.City;

@Repository
public interface CityRepository extends MongoRepository<City, ObjectId> {

    City findCityByCode(Integer code);
}
