package top.gorojack.supershop.controller.api;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import top.gorojack.supershop.annotation.LoginRequired;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.exception.LoginException;
import top.gorojack.supershop.pojo.User;
import top.gorojack.supershop.pojo.dto.UserInfoDto;
import top.gorojack.supershop.service.UserService;
import top.gorojack.supershop.utils.Constant;
import top.gorojack.supershop.utils.JWTUtils;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/user")
public class ApiUserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public R login(@RequestBody User user) {
        String token = userService.login(user.getUsername(), user.getPassword());
        if (null == token) return R.fail(Constant.LOGIN_FAILED);
        Map<String, String> result = new HashMap<>();
        result.put("token", token);
        return R.ok(Constant.LOGIN_SUCCESSFUL, result);
    }

    @LoginRequired
    @GetMapping("/info")
    public R info(HttpServletRequest request) throws LoginException {
        String token = request.getHeader("token");
        User user = JWTUtils.parseJWT(token);
        if (null == user) throw new LoginException();
        User dbUser = userService.findById(user.getUid());
        UserInfoDto infoDto = new UserInfoDto(dbUser);
        return R.ok(infoDto);
    }
}
