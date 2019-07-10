package cn.lanlan.core.controller;

import cn.lanlan.core.pojo.entity.LLResult;
import cn.lanlan.core.pojo.seller.Seller;
import cn.lanlan.core.service.LLSellerService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/seller")
public class LLSellerController {

    @Reference
    private LLSellerService sellerService;
    @RequestMapping("/add")
    public LLResult sellerAdd(@RequestBody Seller seller) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
       String  password =  bCryptPasswordEncoder.encode(seller.getPassword());
       seller.setPassword(password);
        return sellerService.addSeller(seller);
    }




}



