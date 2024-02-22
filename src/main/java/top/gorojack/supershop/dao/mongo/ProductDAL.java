package top.gorojack.supershop.dao.mongo;

import top.gorojack.supershop.pojo.Product;

import java.util.List;

public interface ProductDAL {

    List<Product> findByRandom(Integer size);
}
