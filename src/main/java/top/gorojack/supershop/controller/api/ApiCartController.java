package top.gorojack.supershop.controller.api;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import top.gorojack.supershop.annotation.LoginRequired;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.pojo.Cart;
import top.gorojack.supershop.pojo.User;
import top.gorojack.supershop.service.CartService;
import top.gorojack.supershop.utils.Constant;
import top.gorojack.supershop.utils.JWTUtils;

@RestController
@RequestMapping("/v1/api/cart")
public class ApiCartController {

    @Resource
    private CartService cartService;

    @LoginRequired
    @PostMapping("/add")
    public R add(HttpServletRequest request, @RequestBody Cart cart) {
        String token = request.getHeader("token");
        User user = JWTUtils.parseJWT(token);
        Cart add = cartService.add(user, cart);
        if (null == add) return R.fail(Constant.FAILED_TO_ADD_CART);
        return R.ok(Constant.ADDITION_SUCCESSFUL);
    }
}
