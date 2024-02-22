package top.gorojack.supershop.service;

import top.gorojack.supershop.pojo.User;

public interface UserService {

    String login(String username, String password);

    User findById(Long uid);
}
