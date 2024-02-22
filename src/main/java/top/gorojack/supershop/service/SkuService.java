package top.gorojack.supershop.service;

import top.gorojack.supershop.pojo.Sku;
import top.gorojack.supershop.pojo.dto.SkuDetailDto;

import java.util.List;

public interface SkuService {

    List<Sku> findByProductId(String productId);

    List<SkuDetailDto> findSkuDetailByProductId(String productId);
}
