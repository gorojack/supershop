package top.gorojack.supershop.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.gorojack.supershop.dao.maria.UserRepository;
import top.gorojack.supershop.pojo.User;
import top.gorojack.supershop.service.UserService;
import top.gorojack.supershop.utils.JWTUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    public String login(String username, String password) {
        User user = userRepository.getUserByUsernameAndPassword(username, password);
        if (null == user) {
            return null;
        }
        return JWTUtils.createJWT(user);
    }

    @Override
    public User findById(Long uid) {
        return userRepository.findById(uid).get();
    }
}
