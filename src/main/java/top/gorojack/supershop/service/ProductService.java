package top.gorojack.supershop.service;

import org.springframework.data.domain.Page;
import top.gorojack.supershop.pojo.Product;
import top.gorojack.supershop.pojo.dto.ProductDto;

import java.util.List;

public interface ProductService {

    Long count();

    Page<Product> findPage();

    Page<Product> findPage(Integer page, Integer pageSize);

    Page<Product> findPageByQuery(Integer page, Integer pageSize, String brandName);

    ProductDto getProductInfo(String productId);

    boolean update(ProductDto productDto);

    List<Product> findByRandom(Integer size);

    Page<Product> findByNdCategoryId(Integer ndCateId, Integer page, Integer pageSize);

    Page<Product> search(Integer page, Integer pageSize, String query);
}
