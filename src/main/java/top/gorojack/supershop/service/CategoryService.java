package top.gorojack.supershop.service;

import top.gorojack.supershop.pojo.Category;
import top.gorojack.supershop.pojo.CategoryName;

import java.util.List;

public interface CategoryService {
    List<CategoryName> list();

    List<Category> findByNdCategoryId(Integer ndCateId);
}
