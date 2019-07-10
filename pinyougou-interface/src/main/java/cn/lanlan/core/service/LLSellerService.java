package cn.lanlan.core.service;

import cn.lanlan.core.pojo.entity.LLPageResult;
import cn.lanlan.core.pojo.entity.LLResult;
import cn.lanlan.core.pojo.seller.Seller;

public interface LLSellerService {

    LLResult addSeller(Seller seller);

    LLPageResult findSellerList(Integer page,Integer rows ,Seller seller);

    Seller findSellerOneById(String sellerId);

    void updateSellerStatus(String sellerId ,String status);


}
