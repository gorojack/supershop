package top.gorojack.supershop.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import top.gorojack.supershop.dao.mongo.*;
import top.gorojack.supershop.pojo.*;
import top.gorojack.supershop.pojo.dto.ProductDto;
import top.gorojack.supershop.service.ProductService;
import top.gorojack.supershop.utils.UrlUtils;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductRepository productRepository;
    @Resource
    private CategoryRepository categoryRepository;
    @Resource
    private DetailImageRepository detailImageRepository;
    @Resource
    private PreviewImageRepository previewImageRepository;
    @Resource
    private PropRepository propRepository;
    @Resource
    private ProductDAL productDAL;

    public Long count() {
        return productRepository.count();
    }

    public Page<Product> findPage() {
        return findPage(1, 10);
    }

    public Page<Product> findPage(Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Page<Product> productPage = productRepository.findAll(pageRequest);
        return new PageImpl<>(productPage.getContent(), pageRequest, productPage.getTotalElements());
    }

    @Override
    public Page<Product> findPageByQuery(Integer page, Integer pageSize, String query) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Map<String, String> map = UrlUtils.parseUrl(query.trim());
        Integer stCategoryId = Integer.parseInt(map.getOrDefault("stCategoryId", "0"));
        Integer ndCategoryId = Integer.parseInt(map.getOrDefault("ndCategoryId", "0"));
        String brandName = map.getOrDefault("brandName", "");
        List<Category> categories;
        Page<Product> productPage;
        if (stCategoryId != 0) {
            categories = categoryRepository.findCategoriesByStCategoryId(stCategoryId);
            if (ndCategoryId != 0) {
                categories = categoryRepository.findCategoriesByStCategoryIdAndNdCategoryId(stCategoryId, ndCategoryId);
            }
            List<String> pids = new ArrayList<>();
            categories.forEach(item -> {
                pids.add(item.getProductId());
            });
            productPage = productRepository.findProductsByBrandShowNameLikeIgnoreCaseAndProductIdIn(pageRequest, brandName, pids);
        } else {
            productPage = productRepository.findProductsByBrandShowNameLikeIgnoreCase(pageRequest, brandName);
        }

        return new PageImpl<>(productPage.getContent(), pageRequest, productPage.getTotalElements());
    }

    @Override
    public ProductDto getProductInfo(String productId) {
        Product product = productRepository.findProductByProductId(productId);
        if (null == product) return new ProductDto();
        ProductDto dto = new ProductDto(product);
        List<DetailImage> detailImages = detailImageRepository.findDetailImagesByProductId(dto.getProductId());
        List<PreviewImage> previewImages = previewImageRepository.findPreviewImagesByProductId(dto.getProductId());
        Prop prop = propRepository.findPropsByProductId(dto.getProductId());
        dto.setDetailImages(detailImages);
        dto.setPreviewImages(previewImages);
        dto.setProps(prop.getProps());
        return dto;
    }

    @Override
    public boolean update(ProductDto productDto) {
        Product exists = productRepository.findProductByProductId(productDto.getProductId());
        if (null == exists) return false;
        Product product = productRepository.findProductByProductId(productDto.getProductId());
        Prop prop = propRepository.findPropsByProductId(productDto.getProductId());
        BeanUtils.copyProperties(productDto, product);
        prop.setProps(productDto.getProps());
        productRepository.save(product);
        propRepository.save(prop);
        return true;
    }

    @Override
    public List<Product> findByRandom(Integer size) {
        return productDAL.findByRandom(size);
    }

    @Override
    public Page<Product> findByNdCategoryId(Integer ndCateId, Integer page, Integer pageSize) {
        List<Category> categoryList = categoryRepository.findCategoriesByNdCategoryId(ndCateId);
        List<String> productIds = new ArrayList<>();
        categoryList.forEach(item -> {
            productIds.add(item.getProductId());
        });
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Page<Product> productPage = productRepository.findProductsByProductIdIn(pageRequest, productIds);
        return new PageImpl<>(productPage.getContent(), pageRequest, productPage.getTotalElements());
    }
}
