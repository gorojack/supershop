package top.gorojack.supershop.service.impl;

import cn.hutool.core.util.RandomUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import top.gorojack.supershop.dao.maria.UserRepository;
import top.gorojack.supershop.pojo.User;
import top.gorojack.supershop.pojo.dto.UserRegDto;
import top.gorojack.supershop.service.UserService;
import top.gorojack.supershop.utils.JWTUtils;
import top.gorojack.supershop.utils.RedisUtils;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private RedisUtils redisUtils;

    @Value("${jwt.expiration}")
    private Long EXPIRATION;

    public String login(String username, String password) {
        String encryptPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userRepository.getUserByUsernameAndPassword(username, encryptPassword);
        if (null == user) {
            return null;
        }
        String jwt = JWTUtils.createJWT(user);
        redisUtils.set(jwt, "1", EXPIRATION * 1000);
        return jwt;
    }

    @Override
    public User findById(Long uid) {
        return userRepository.findById(uid).get();
    }

    @Override
    public String generateCode(String mail) {
        String code = RandomUtil.randomNumbers(6);
        redisUtils.set(mail, code, 300);
        return code;
    }

    @Override
    public User register(UserRegDto dto) {
        User reg = new User();
        reg.setUsername(dto.getUsername());
        reg.setNickname(dto.getUsername());
        String encryptPassword = DigestUtils.md5DigestAsHex(dto.getPassword().getBytes());
        reg.setPassword(encryptPassword);
        reg.setAvatar("https://images.gorojack.top/i/2024/03/31/66093c8d285ab.png");
        reg.setRole("USER");
        return userRepository.save(reg);
    }

    @Override
    public User checkUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public void logout(String jwt) {
        redisUtils.delete(jwt);
    }
}
