package cn.lanlan.core.controller;

import cn.lanlan.core.pojo.entity.LLPageResult;
import cn.lanlan.core.pojo.entity.LLResult;
import cn.lanlan.core.pojo.seller.Seller;
import cn.lanlan.core.service.LLSellerService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/seller")
@RestController
public class LLSeller1Controller {

    @Reference
    private LLSellerService sellerService;

    @RequestMapping("/search")
    public LLPageResult search(Integer page, Integer rows, @RequestBody(required = false) Seller seller)
    {
        return   sellerService.findSellerList(page, rows, seller);
    }

    @RequestMapping("/findOne")
    public Seller findSellerOne( @RequestParam("id") String sellerId) {
        try {
            sellerId = new String(sellerId.getBytes("ISO-8859-1"), "utf-8");
            return sellerService.findSellerOneById(sellerId);
        }
        catch (Exception e) {
            return new Seller();
        }
    }

    @RequestMapping("/updateStatus")
    public LLResult updateSellerStatus(@RequestParam("sellerId") String sellerId,@RequestParam("status")String stauts) {
        try {
            sellerId = new String(sellerId.getBytes("ISO-8859-1"), "utf-8");
            sellerService.updateSellerStatus(sellerId, stauts);
            return  new LLResult(false, "审核成功");
        }catch (Exception e) {
            e.printStackTrace();
            return  new LLResult(false, "审核失败");
        }
    }
}
