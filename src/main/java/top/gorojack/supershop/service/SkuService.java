package top.gorojack.supershop.service;

import top.gorojack.supershop.pojo.Sku;

import java.util.List;

public interface SkuService {

    List<Sku> findByProductId(String productId);
}
