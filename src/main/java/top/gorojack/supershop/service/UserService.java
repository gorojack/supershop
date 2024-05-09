package top.gorojack.supershop.service;

import org.springframework.data.domain.Page;
import top.gorojack.supershop.pojo.User;
import top.gorojack.supershop.pojo.dto.UserRegDto;

public interface UserService {

    String login(String username, String password);

    User findById(Long uid);

    String generateCode(String mail);

    User register(UserRegDto dto);

    User checkUsername(String username);

    void logout(String jwt);

    Page<User> findPage(Integer page, Integer pageSize);

    Page<User> findPageQuery(Integer page, Integer pageSize, String query);

    User update(User user);
}
