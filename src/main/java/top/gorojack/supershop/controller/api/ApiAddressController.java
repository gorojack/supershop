package top.gorojack.supershop.controller.api;

import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.*;
import top.gorojack.supershop.annotation.LoginRequired;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.handler.UserInfoThreadHolder;
import top.gorojack.supershop.pojo.Address;
import top.gorojack.supershop.pojo.User;
import top.gorojack.supershop.pojo.dto.AddressDto;
import top.gorojack.supershop.service.AddressService;
import top.gorojack.supershop.utils.Constant;

import java.util.List;

@RestController
@RequestMapping("/v1/api/address")
public class ApiAddressController {

    @Resource
    private AddressService addressService;

    @LoginRequired
    @GetMapping("/list")
    public R list() {
        User user = UserInfoThreadHolder.getCurrentUser();
        List<AddressDto> addressDtoList = addressService.findByUid(user.getUid());
        return R.ok(addressDtoList);
    }

    @LoginRequired
    @PostMapping("/add")
    public R add(@RequestBody Address address) {
        Address save = addressService.save(address);
        if (null == save) return R.fail(Constant.ADDITION_FAILED);
        return R.ok(Constant.ADDITION_SUCCESSFUL);
    }

    @LoginRequired
    @PostMapping("/delete")
    public R delete(@RequestBody Address address) {
        addressService.deleteById(address.getId());
        return R.ok(Constant.DELETE_SUCCESSFUL);
    }
}
