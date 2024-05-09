package top.gorojack.supershop.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import top.gorojack.supershop.pojo.Banner;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NavigateDto {
    private Long id;
    private String navUrl;
    private String imageUrl;
    private String title;

    public NavigateDto(Banner item) {
        BeanUtils.copyProperties(item, this);
    }
}
