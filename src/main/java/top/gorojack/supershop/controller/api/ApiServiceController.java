package top.gorojack.supershop.controller.api;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.pojo.dto.NavigateDto;
import top.gorojack.supershop.service.BannerService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/api/service")
public class ApiServiceController {

    @Resource
    private BannerService bannerService;

    @GetMapping("/banner")
    public R banner() {
        List<NavigateDto> bannerList = bannerService.list();
        return R.ok(bannerList);
    }
}
