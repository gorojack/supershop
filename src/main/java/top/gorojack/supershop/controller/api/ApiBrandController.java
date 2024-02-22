package top.gorojack.supershop.controller.api;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.pojo.Brand;
import top.gorojack.supershop.service.BrandService;

@RestController
@RequestMapping("/v1/api/brand")
public class ApiBrandController {

    @Resource
    private BrandService brandService;

    @GetMapping("/info/{brandId}")
    public R info(@PathVariable String brandId){
        Brand brand = brandService.findByBrandId(brandId);
        return R.ok(brand);
    }
}
