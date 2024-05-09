package top.gorojack.supershop.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.gorojack.supershop.dao.maria.BannerRepository;
import top.gorojack.supershop.pojo.Banner;
import top.gorojack.supershop.pojo.dto.NavigateDto;
import top.gorojack.supershop.service.BannerService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Resource
    private BannerRepository bannerRepository;

    @Override
    public List<NavigateDto> list() {
        List<Banner> bannerList = bannerRepository.findAll();
        List<NavigateDto> navigateDtoList = new ArrayList<>();
        bannerList.forEach(item -> {
            NavigateDto dto = new NavigateDto(item);
            navigateDtoList.add(dto);
        });
        return navigateDtoList;
    }
}
