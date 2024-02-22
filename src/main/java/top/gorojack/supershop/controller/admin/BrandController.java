package top.gorojack.supershop.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import top.gorojack.supershop.annotation.AdminRequired;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.pojo.Brand;
import top.gorojack.supershop.service.BrandService;

@RestController
@RequestMapping("/v1/admin/brand")
public class BrandController {

    @Resource
    private BrandService brandService;

    @AdminRequired
    @GetMapping("/page/{page}/{pageSize}")
    public R page(@PathVariable Integer page, @PathVariable Integer pageSize) {
        Page<Brand> brandPage = brandService.findPage(page, pageSize);
        return R.ok(brandPage);
    }

    @AdminRequired
    @GetMapping("/page/{page}/{pageSize}/{query}")
    public R page(@PathVariable Integer page, @PathVariable Integer pageSize, @PathVariable String query) {
        Page<Brand> brandPage = brandService.findPage(page, pageSize, query);
        return R.ok(brandPage);
    }

    @AdminRequired
    @GetMapping("/info/{brandId}")
    public R info(@PathVariable String brandId) {
        Brand brand = brandService.findByBrandId(brandId);
        return R.ok(brand);
    }

    @AdminRequired
    @PostMapping("/update")
    public R update(@RequestBody Brand brand) {
        // TODO The api of update brand
        return R.ok();
    }
}
