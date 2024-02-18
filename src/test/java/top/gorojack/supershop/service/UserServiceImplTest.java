package top.gorojack.supershop.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.gorojack.supershop.service.impl.UserServiceImpl;

@SpringBootTest
class UserServiceImplTest {

    @Resource
    private UserServiceImpl userService;

    @Test
    void login() {
        String token = userService.login("admin", "123456");
        System.out.println(token);
    }
}