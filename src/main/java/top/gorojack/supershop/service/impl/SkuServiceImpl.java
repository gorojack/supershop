package top.gorojack.supershop.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.gorojack.supershop.dao.mongo.SalePropRepository;
import top.gorojack.supershop.dao.mongo.SkuRepository;
import top.gorojack.supershop.pojo.SaleProp;
import top.gorojack.supershop.pojo.Sku;
import top.gorojack.supershop.pojo.dto.SkuDetailDto;
import top.gorojack.supershop.service.SkuService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {

    @Resource
    private SkuRepository skuRepository;
    @Resource
    private SalePropRepository salePropRepository;

    @Override
    public List<Sku> findByProductId(String productId) {
        return skuRepository.findSkusByProductId(productId);
    }

    @Override
    public List<SkuDetailDto> findSkuDetailByProductId(String productId) {
        List<Sku> skuList = findByProductId(productId);
        List<SkuDetailDto> skuDetailDtoList = new ArrayList<>();
        skuList.forEach(item -> {
            SkuDetailDto dto = new SkuDetailDto(item);
            List<SaleProp> salePropList = salePropRepository.findSalePropsByProductId(productId);
            dto.setSaleProps(salePropList);
            skuDetailDtoList.add(dto);
        });
        return skuDetailDtoList;
    }
}
