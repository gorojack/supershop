package top.gorojack.supershop.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.gorojack.supershop.service.impl.ProductServiceImpl;

@SpringBootTest
class ProductServiceImplTest {

    @Resource
    private ProductServiceImpl productService;

    @Test
    void findAll() {
//        List<Product> productList = productService.findPage();
    }
}