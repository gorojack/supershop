package top.gorojack.supershop.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.gorojack.supershop.dao.mongo.SkuRepository;
import top.gorojack.supershop.pojo.Sku;
import top.gorojack.supershop.service.SkuService;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {

    @Resource
    private SkuRepository skuRepository;

    @Override
    public List<Sku> findByProductId(String productId) {
        return skuRepository.findSkusByProductId(productId);
    }
}
