package top.gorojack.supershop.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gorojack.supershop.annotation.AdminRequired;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.pojo.dto.NavigateDto;
import top.gorojack.supershop.service.BannerService;

import java.util.List;

@RestController
@RequestMapping("/v1/admin/banner")
public class BannerController {

    @Resource
    private BannerService bannerService;

    @AdminRequired
    @GetMapping("/list")
    public R list() {
        List<NavigateDto> list = bannerService.list();
        return R.ok(list);
    }
}
