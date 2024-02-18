package top.gorojack.supershop.dao.mongo;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.gorojack.supershop.pojo.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Resource
    private ProductRepository productRepository;

    @Test
    public void testFindAll() {
        List<Product> productList = productRepository.findAll();
        System.out.println(productList.get(0));
    }

    @Test
    void findProductsByBrandShowNameLikeIgnoreCase() {
    }

    @Test
    void findProductByProductId() {
        Product product = productRepository.findProductByProductId("6920491581686334537");
        System.out.println(product);
    }
}