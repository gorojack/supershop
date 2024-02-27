package top.gorojack.supershop.controller.api;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gorojack.supershop.annotation.LoginRequired;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.pojo.dto.OrderConfirmDto;
import top.gorojack.supershop.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/v1/api/order")
public class ApiOrderController {

    @Resource
    private OrderService orderService;

    @LoginRequired
    @PostMapping("/confirm")
    public R confirm(@RequestBody List<Long> cartIds) {
        OrderConfirmDto dto = orderService.confirmOrder(cartIds);
        return R.ok(dto);
    }

    @LoginRequired
    @PostMapping("/create")
    public R create(@RequestBody List<Long> cartIds) {
        Integer result = orderService.createOrder(cartIds);
        return R.ok();
    }
}
