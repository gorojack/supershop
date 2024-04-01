package top.gorojack.supershop.controller.api;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import top.gorojack.supershop.annotation.LoginRequired;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.exception.LoginException;
import top.gorojack.supershop.pojo.User;
import top.gorojack.supershop.pojo.dto.MailDto;
import top.gorojack.supershop.pojo.dto.UserInfoDto;
import top.gorojack.supershop.pojo.dto.UserRegDto;
import top.gorojack.supershop.service.MailService;
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
    @Resource
    private MailService mailService;

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

    @GetMapping("/code/{mail}")
    public R code(@PathVariable String mail) {
        String code = userService.generateCode(mail);
        MailDto mailDto = new MailDto();
        String[] tos = {mail};
        mailDto.setTos(tos);
        mailDto.setSubject("验证码");
        mailDto.setContent("您的验证码是:" + code);
        System.out.println(code);
        mailService.sendMail(mailDto);
        return R.ok();
    }

    @GetMapping("/check/username/{username}")
    public R checkUsername(@PathVariable String username) {
        User user = userService.checkUsername(username);
        if (null != user) {
            return R.fail(Constant.THE_USER_ALREADY_EXISTS);
        }
        return R.ok();
    }

    @PostMapping("/register")
    public R register(@RequestBody UserRegDto dto) {
        if (null == dto.getUsername() || null == dto.getPassword() || null == dto.getEmail() || null == dto.getCode())
            return R.fail("参数错误");
        User reg = userService.register(dto);
        if (null == reg) return R.fail(Constant.REGISTRATION_FAILED);
        return R.ok(Constant.REGISTRATION_SUCCESSFUL);
    }

    @LoginRequired
    @GetMapping("/logout")
    public R logout(HttpServletRequest request) {
        String jwt = request.getHeader("token");
        userService.logout(jwt);
        return R.ok();
    }
}
