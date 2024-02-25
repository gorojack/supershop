package top.gorojack.supershop.controller.api;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import top.gorojack.supershop.annotation.LoginRequired;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.pojo.Cart;
import top.gorojack.supershop.pojo.User;
import top.gorojack.supershop.pojo.dto.CartDto;
import top.gorojack.supershop.service.CartService;
import top.gorojack.supershop.utils.Constant;
import top.gorojack.supershop.utils.JWTUtils;

import java.util.List;

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

    @LoginRequired
    @GetMapping("/list")
    public R list(HttpServletRequest request) {
        String token = request.getHeader("token");
        User user = JWTUtils.parseJWT(token);
        List<CartDto> userCart = cartService.findUserCart(user.getUid());
        return R.ok(userCart);
    }

    @LoginRequired
    @PostMapping("/updateNumber")
    public R updateNumber(@RequestBody Cart cart) {
        if (null == cart.getId() || null == cart.getNumber() || cart.getNumber() <= 0)
            return R.fail(Constant.ILLEGAL_ARGUMENTS);
        Cart result = cartService.updateNumber(cart);
        if (null == result) return R.fail(Constant.OPERATION_FAILED);
        return R.ok();
    }

    @LoginRequired
    @PostMapping("/delete")
    public R delete(@RequestBody List<Long> ids){
        cartService.deleteByIds(ids);
        return R.ok();
    }
}
