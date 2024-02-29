package top.gorojack.supershop.controller.api;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.gorojack.supershop.annotation.LoginRequired;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.pojo.dto.OrderConfirmDto;
import top.gorojack.supershop.pojo.dto.OrderDto;
import top.gorojack.supershop.service.OrderService;
import top.gorojack.supershop.utils.Constant;

import java.util.List;
import java.util.Map;

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
    public R create(@RequestBody OrderConfirmDto dto) {
        Integer result = orderService.createOrder(dto);
        return R.ok(Constant.CREATE_SUCCESSFUL);
    }

    @LoginRequired
    @GetMapping("/list/{type}/{page}/{pageSize}")
    public R list(@PathVariable Integer type, @PathVariable Integer page, @PathVariable Integer pageSize) {
        Map<String, List<OrderDto>> result = orderService.list(type, page, pageSize);
        return R.ok(result);
    }

    @LoginRequired
    @GetMapping("/status")
    public R status() {
        Map<String, Integer> result = orderService.getUserStatus();
        return R.ok(result);
    }
}
