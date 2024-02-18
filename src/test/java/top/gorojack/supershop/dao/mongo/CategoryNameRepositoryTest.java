package top.gorojack.supershop.dao.mongo;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.gorojack.supershop.pojo.CategoryName;

import java.util.List;

@SpringBootTest
class CategoryNameRepositoryTest {

    @Resource
    private CategoryNameRepository categoryNameRepository;

    @Test
    void test() {
        // stCategoryId ndCategoryId
//        List<CategoryName> cate = categoryNameRepository.findAll();
        List<CategoryName> cate = categoryNameRepository.findCategoryNamesByStCategoryIdAndNdCategoryId(0,0);
        CategoryName cate1 = categoryNameRepository.findCategoryNameByCategoryId(10010540);
        System.out.println(cate1);
        System.out.println(cate);
    }
}