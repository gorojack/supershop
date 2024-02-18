package top.gorojack.supershop.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.pojo.CategoryName;
import top.gorojack.supershop.service.CategoryNameService;

import java.util.List;

@RestController
@RequestMapping("/v1/admin/category")
public class CategoryController {

    @Resource
    private CategoryNameService categoryNameService;

    @GetMapping("/list/{stCateId}/{ndCateId}")
    public R list(@PathVariable Integer stCateId, @PathVariable Integer ndCateId) {
        List<CategoryName> categoryList = categoryNameService.listCategoryName(stCateId, ndCateId);
        return R.ok(categoryList);
    }
}
