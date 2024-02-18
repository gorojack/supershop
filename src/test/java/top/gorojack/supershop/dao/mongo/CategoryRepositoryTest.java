package top.gorojack.supershop.dao.mongo;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import top.gorojack.supershop.pojo.Category;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Resource
    private CategoryRepository categoryRepository;

    @Test
    public void test() {
        Category category = new Category();
        category.setStCategoryId(30074);
        Example<Category> example = Example.of(category);
        List<Category> categories = categoryRepository.findCategoriesByStCategoryId(30074);
        System.out.println(categories);
    }
}