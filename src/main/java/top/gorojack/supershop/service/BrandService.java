package top.gorojack.supershop.service;

import org.springframework.data.domain.Page;
import top.gorojack.supershop.pojo.Brand;

public interface BrandService {

    Brand findByBrandId(String brandId);

    Brand findByBrandSn(String brandSn);

    Page<Brand> findPage(Integer page, Integer pageSize);

    Page<Brand> findPage(Integer page, Integer pageSize, String query);
}
