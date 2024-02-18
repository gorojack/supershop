package top.gorojack.supershop.controller.admin;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.pojo.User;
import top.gorojack.supershop.service.impl.UserServiceImpl;
import top.gorojack.supershop.utils.Constant;
import top.gorojack.supershop.utils.JWTUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/admin/user")
public class UserController {

    @Resource
    private UserServiceImpl userService;

    @PostMapping("/login")
    public R login(@RequestBody User user) {
        String token = userService.login(user.getUsername(), user.getPassword());
        if (null == token) return R.fail(Constant.LOGIN_FAILED);
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        return R.ok(Constant.LOGIN_SUCCESSFUL, data);
    }

    @GetMapping("/info")
    public R info(HttpServletRequest request) {
        String token = request.getHeader("token");
        User user = JWTUtils.parseJWT(token);
        Map<String, Object> info = new HashMap<>();
        info.put("roles", Arrays.asList("admin"));
        info.put("introduction", "I am a super administrator");
        info.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        info.put("name", "Super Admin");
        return R.ok(info);
    }
}
