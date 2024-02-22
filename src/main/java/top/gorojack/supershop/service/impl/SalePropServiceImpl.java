package top.gorojack.supershop.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.gorojack.supershop.dao.mongo.SalePropRepository;
import top.gorojack.supershop.dao.mongo.SkuRepository;
import top.gorojack.supershop.pojo.SaleProp;
import top.gorojack.supershop.pojo.Sku;
import top.gorojack.supershop.service.SalePropService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalePropServiceImpl implements SalePropService {

    @Resource
    private SalePropRepository salePropRepository;
    @Resource
    private SkuRepository skuRepository;

    @Override
    public List<SaleProp> findByProductId(String productId) {
        // TODO
        return new ArrayList<>();
    }
}
