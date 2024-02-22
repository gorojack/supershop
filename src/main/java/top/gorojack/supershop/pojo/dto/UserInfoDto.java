package top.gorojack.supershop.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import top.gorojack.supershop.pojo.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private String uid;
    private String username;
    private String nickname;
    private String phone;
    private String email;
    private String avatar;

    public UserInfoDto(User user) {
        BeanUtils.copyProperties(user, this);
    }
}
