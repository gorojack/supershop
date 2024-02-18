package top.gorojack.supershop.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.gorojack.supershop.dao.mongo.CategoryNameRepository;
import top.gorojack.supershop.pojo.CategoryName;
import top.gorojack.supershop.service.CategoryNameService;

import java.util.List;

@Service
public class CategoryNameServiceImpl implements CategoryNameService {

    @Resource
    private CategoryNameRepository categoryNameRepository;

    @Override
    public List<CategoryName> listCategoryName(Integer stCateId, Integer ndCateId) {
        return categoryNameRepository.findCategoryNamesByStCategoryIdAndNdCategoryId(stCateId, ndCateId);
    }
}
