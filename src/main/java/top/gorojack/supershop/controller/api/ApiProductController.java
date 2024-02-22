package top.gorojack.supershop.controller.api;

import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.pojo.Product;
import top.gorojack.supershop.pojo.dto.ProductDto;
import top.gorojack.supershop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/v1/api/product")
public class ApiProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/recommend")
    public R recommend() {
        List<Product> products = productService.findByRandom(10);
        return R.ok(products);
    }

    @GetMapping("/listNdC/{ndCateId}/{page}/{pageSize}")
    public R listByNdCate(@PathVariable Integer ndCateId, @PathVariable Integer page, @PathVariable Integer pageSize) {
        Page<Product> productPage = productService.findByNdCategoryId(ndCateId, page, pageSize);
        return R.ok(productPage);
    }

    @GetMapping("/info/{productId}")
    public R info(@PathVariable String productId) {
        ProductDto productInfo = productService.getProductInfo(productId);
        return R.ok(productInfo);
    }
}
