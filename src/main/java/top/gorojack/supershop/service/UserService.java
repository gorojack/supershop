package top.gorojack.supershop.service;

import top.gorojack.supershop.pojo.User;
import top.gorojack.supershop.pojo.dto.UserRegDto;

public interface UserService {

    String login(String username, String password);

    User findById(Long uid);

    String generateCode(String mail);

    User register(UserRegDto dto);

    User checkUsername(String username);

    void logout(String jwt);
}
