package top.gorojack.supershop.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.pojo.Product;
import top.gorojack.supershop.pojo.Sku;
import top.gorojack.supershop.pojo.dto.ProductDto;
import top.gorojack.supershop.service.ProductService;
import top.gorojack.supershop.service.SkuService;

import java.util.List;

@RestController
@RequestMapping("/v1/admin/product")
public class ProductController {

    @Resource
    private ProductService productService;
    @Resource
    private SkuService skuService;

    @GetMapping("/page/{page}/{pageSize}")
    public R page(@PathVariable Integer page, @PathVariable Integer pageSize) {
        Page<Product> productPage = productService.findPage(page, pageSize);
        return R.ok(productPage);
    }

    @GetMapping("/page/{page}/{pageSize}/{query}")
    public R pageQuery(@PathVariable Integer page, @PathVariable Integer pageSize, @PathVariable String query) {
        Page<Product> productPage = productService.findPageByQuery(page, pageSize, query);
        return R.ok(productPage);
    }

    @GetMapping("/info/{productId}")
    public R info(@PathVariable String productId) {
        ProductDto productInfo = productService.getProductInfo(productId);
        return R.ok(productInfo);
    }

    @GetMapping("/sku/{productId}")
    public R sku(@PathVariable String productId) {
        List<Sku> skus = skuService.findByProductId(productId);
        return R.ok(skus);
    }

    @PostMapping("/update")
    public R update(@RequestBody ProductDto productDto) {
        boolean update = productService.update(productDto);
        if (!update) return R.fail("修改失败");
        return R.ok("修改成功");
    }
}
