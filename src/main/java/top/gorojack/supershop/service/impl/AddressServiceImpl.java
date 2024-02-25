package top.gorojack.supershop.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.gorojack.supershop.dao.maria.AddressRepository;
import top.gorojack.supershop.dao.mongo.CityRepository;
import top.gorojack.supershop.handler.UserInfoThreadHolder;
import top.gorojack.supershop.pojo.Address;
import top.gorojack.supershop.pojo.City;
import top.gorojack.supershop.pojo.User;
import top.gorojack.supershop.pojo.dto.AddressDto;
import top.gorojack.supershop.service.AddressService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressRepository addressRepository;
    @Resource
    private CityRepository cityRepository;

    @Override
    public List<AddressDto> findByUid(Long uid) {
        List<Address> addressList = addressRepository.findAddressByUid(uid);
        List<AddressDto> result = new ArrayList<>();
        addressList.forEach(item -> {
            AddressDto dto = new AddressDto(item);
            String fullCityName = getFullCity(dto.getCityCode());
            dto.setFullName(fullCityName);
            result.add(dto);
        });
        return result;
    }

    @Override
    public Address save(Address address) {
        User user = UserInfoThreadHolder.getCurrentUser();
        if (1 == address.getIsDefault()) {
            addressRepository.clearUserDefaultAddress(user.getUid());
        }
        List<Address> addressList = addressRepository.findAddressByUid(user.getUid());
        if (addressList.isEmpty()) {
            address.setIsDefault(1);
        }
        address.setUid(user.getUid());
        return addressRepository.save(address);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    private String getFullCity(Integer code) {
        City city = cityRepository.findCityByCode(code);
        String parentCityName = "";
        if (0 != city.getParentCode()) {
            parentCityName = getFullCity(city.getParentCode());
        }
        if (parentCityName.isEmpty()) return city.getName();
        return parentCityName + "/" + city.getName();
    }
}
