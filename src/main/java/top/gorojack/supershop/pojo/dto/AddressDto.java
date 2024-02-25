package top.gorojack.supershop.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import top.gorojack.supershop.pojo.Address;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto extends Address {

    private String fullName;

    public AddressDto(Address address) {
        BeanUtils.copyProperties(address, this);
    }
}
