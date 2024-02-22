package top.gorojack.supershop.service;

import top.gorojack.supershop.pojo.SaleProp;

import java.util.List;

public interface SalePropService {

    List<SaleProp> findByProductId(String productId);
}
