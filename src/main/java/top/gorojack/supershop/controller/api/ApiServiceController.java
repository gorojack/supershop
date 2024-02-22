package top.gorojack.supershop.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.pojo.dto.NavigateDto;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/api/service")
public class ApiServiceController {

    @GetMapping("/banner")
    public R banner() {
        List<NavigateDto> bannerList = Arrays.asList(
                new NavigateDto("", "https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/bi001/2023/0811/98/ias_d6665f86b0d9d48bf9af6b2c5e6f23e5_1135x545_85.jpg", "珀莱雅PROYA面部护理专场"),
                new NavigateDto("", "https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/bi001/2023/0806/147/ias_ca5d7d74fb2855d06c302bd0b5180169_1135x545_85.jpg", "曼妮芬Maniform内衣专场"),
                new NavigateDto("", "https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/bi001/2023/0906/34/ias_d07b383e47427ba6b7975f9b535f4c65_1135x545_85.jpg", "马骑顿MQD母婴鞋服专场"),
                new NavigateDto("","https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/bi001/2023/0908/180/ias_b6badada2d7eeb5c00ed22483b59e2c1_1135x545_85.jpg","MARC&JANIE母婴鞋服专场")
        );
        return R.ok(bannerList);
    }
}
