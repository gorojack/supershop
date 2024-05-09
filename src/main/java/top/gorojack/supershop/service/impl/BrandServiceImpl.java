package top.gorojack.supershop.service.impl;

import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import top.gorojack.supershop.dao.mongo.BrandRepository;
import top.gorojack.supershop.pojo.Brand;
import top.gorojack.supershop.service.BrandService;
import top.gorojack.supershop.utils.UrlUtils;

import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandRepository brandRepository;

    @Override
    public Brand findByBrandId(String brandId) {
        return brandRepository.findBrandByBrandId(brandId);
    }

    @Override
    public Brand findByBrandSn(String brandSn) {
        return brandRepository.findBrandByBrandSn(brandSn);
    }

    @Override
    public Page<Brand> findPage(Integer page, Integer pageSize) {
        return findPage(page, pageSize, "");
    }

    @Override
    public Page<Brand> findPage(Integer page, Integer pageSize, String query) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Map<String, String> map = UrlUtils.parseUrl(query.trim());
        Page<Brand> brandPage;
        String name = map.getOrDefault("name", "");
        if (name.isEmpty()) {
            brandPage = brandRepository.findAll(pageRequest);
        } else {
            brandPage = brandRepository.findBrandsByNameLikeIgnoreCase(pageRequest, name);
        }
        return new PageImpl<>(brandPage.getContent(), pageRequest, brandPage.getTotalElements());
    }

    @Override
    public Brand update(Brand brand) {
        return brandRepository.save(brand);
    }
}
