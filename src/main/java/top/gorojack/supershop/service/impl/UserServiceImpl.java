package top.gorojack.supershop.service.impl;

import cn.hutool.core.util.RandomUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import top.gorojack.supershop.dao.maria.UserRepository;
import top.gorojack.supershop.pojo.User;
import top.gorojack.supershop.pojo.dto.UserRegDto;
import top.gorojack.supershop.service.UserService;
import top.gorojack.supershop.utils.JWTUtils;
import top.gorojack.supershop.utils.RedisUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private RedisUtils redisUtils;

    @Value("${jwt.expiration}")
    private Long EXPIRATION;

    @Override
    public String login(String username, String password) {
        String encryptPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userRepository.getUserByUsernameAndPassword(username, encryptPassword);
        if (null == user) {
            return null;
        }
        String jwt = JWTUtils.createJWT(user); // 根据用户实体类属性生成JWT
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
        String code = redisUtils.get(dto.getEmail());
        if (null == code || !code.equals(dto.getCode()))
            return null;
        reg.setUsername(dto.getUsername());
        reg.setNickname(dto.getUsername());
        reg.setEmail(dto.getEmail());
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

    @Override
    public Page<User> findPage(Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Page<User> userPage = userRepository.findAll(pageRequest);
        return new PageImpl<>(userPage.getContent(), pageRequest, userPage.getTotalElements());
    }

    @Override
    public Page<User> findPageQuery(Integer page, Integer pageSize, String query) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Page<User> userPage = userRepository.findUsersByUsernameLikeIgnoreCase(pageRequest, "%" + query + "%");
        return new PageImpl<>(userPage.getContent(), pageRequest, userPage.getTotalElements());
    }

    @Override
    public User update(User user) {
        Long uid = user.getUid();
        if (null == uid) {
            String encryptPassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            user.setPassword(encryptPassword);
            return userRepository.save(user);
        }
        Optional<User> exist = userRepository.findById(uid);
        if (exist.isEmpty()) {
            String encryptPassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            user.setPassword(encryptPassword);
            return userRepository.save(user);
        }
        User existUser = exist.get();
        if (user.getPassword().isEmpty()) {
            user.setPassword(existUser.getPassword());
        } else {
            String encryptPassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            user.setPassword(encryptPassword);
        }
        return userRepository.save(user);
    }
}
