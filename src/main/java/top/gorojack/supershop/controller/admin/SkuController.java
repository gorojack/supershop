package top.gorojack.supershop.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gorojack.supershop.annotation.AdminRequired;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.service.SalePropService;
import top.gorojack.supershop.service.SkuService;

@RestController
@RequestMapping("/v1/admin/sku")
public class SkuController {

    @Resource
    private SkuService skuService;

    @Resource
    private SalePropService salePropService;

    @AdminRequired
    @GetMapping("/list/{productId}")
    public R list(@PathVariable String productId) {
        // TODO get product sku
        return R.ok();
    }

    @AdminRequired
    @GetMapping("/prop/{productId}")
    public R prop(@PathVariable String productId) {
        // TODO get sku info
        return R.ok();
    }
}
