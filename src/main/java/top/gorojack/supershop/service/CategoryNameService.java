package top.gorojack.supershop.service;

import top.gorojack.supershop.pojo.CategoryName;

import java.util.List;

public interface CategoryNameService {

    List<CategoryName> listCategoryName(Integer stCateId, Integer ndCateId);
}
