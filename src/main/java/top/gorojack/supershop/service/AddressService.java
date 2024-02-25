package top.gorojack.supershop.service;

import top.gorojack.supershop.pojo.Address;
import top.gorojack.supershop.pojo.dto.AddressDto;

import java.util.List;

public interface AddressService {

    List<AddressDto> findByUid(Long uid);

    Address save(Address address);

    void deleteById(Long id);
}
