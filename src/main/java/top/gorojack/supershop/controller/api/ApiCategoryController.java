package top.gorojack.supershop.controller.api;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.pojo.CategoryName;
import top.gorojack.supershop.pojo.dto.NavigateDto;
import top.gorojack.supershop.service.CategoryService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/api/category")
public class ApiCategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/recommend")
    public R recommend() {
        List<NavigateDto> recommendList = Arrays.asList(
                new NavigateDto(1L, "/pages/category/category?cate=52020711", "https://images.gorojack.top/i/2024/02/21/65d5554c25d01.png", "服饰"),
                new NavigateDto(2L, "/pages/category/category?cate=53307451", "https://images.gorojack.top/i/2024/02/21/65d55972e6ec4.png", "鞋子"),
                new NavigateDto(3L, "/pages/category/category?cate=10010406", "https://images.gorojack.top/i/2024/02/21/65d5563d77ed1.png", "护肤品"),
                new NavigateDto(4L, "/pages/category/category?cate=52385071", "https://images.gorojack.top/i/2024/02/21/65d55a534d549.png", "数码")
        );
        return R.ok(recommendList);
    }

    @GetMapping("/list")
    public R list() {
        List<CategoryName> categories = categoryService.list();
        return R.ok(categories);
    }
}
