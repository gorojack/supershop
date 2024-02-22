package top.gorojack.supershop.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.gorojack.supershop.dao.mongo.CategoryNameRepository;
import top.gorojack.supershop.dao.mongo.CategoryRepository;
import top.gorojack.supershop.pojo.Category;
import top.gorojack.supershop.pojo.CategoryName;
import top.gorojack.supershop.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryRepository categoryRepository;
    @Resource
    private CategoryNameRepository categoryNameRepository;

    @Override
    public List<CategoryName> list() {
        return categoryNameRepository.findAllNdCategory();
    }

    @Override
    public List<Category> findByNdCategoryId(Integer ndCateId) {
        return categoryRepository.findCategoriesByNdCategoryId(ndCateId);
    }
}
