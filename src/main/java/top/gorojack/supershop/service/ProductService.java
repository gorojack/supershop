package top.gorojack.supershop.service;

import org.springframework.data.domain.Page;
import top.gorojack.supershop.pojo.Product;
import top.gorojack.supershop.pojo.dto.ProductDto;

public interface ProductService {

    Long count();

    Page<Product> findPage();

    Page<Product> findPage(Integer page, Integer pageSize);

    Page<Product> findPageByQuery(Integer page, Integer pageSize, String brandName);

    ProductDto getProductInfo(String productId);

    boolean update(ProductDto productDto);
}
