package top.gorojack.supershop.dao.mongo.impl;

import jakarta.annotation.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.stereotype.Repository;
import top.gorojack.supershop.dao.mongo.ProductDAL;
import top.gorojack.supershop.pojo.Product;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class ProductDALImpl implements ProductDAL {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<Product> findByRandom(Integer size) {
        AggregationOperation sampleOperation = sample(size);
        Aggregation aggregation = newAggregation(sampleOperation);
        return mongoTemplate.aggregate(aggregation, "product", Product.class).getMappedResults();
    }
}
