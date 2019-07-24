package cn.lanlan.core.controller;

import cn.lanlan.core.pojo.entity.LLResult;
import cn.lanlan.core.pojo.pojogroup.LLGoodsGroup;
import cn.lanlan.core.service.LLGoodsService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class LLGoodsController {
    @Reference
    private LLGoodsService goodsService;
    @RequestMapping("/add")
    public LLResult addGoods(@RequestBody LLGoodsGroup goodsGroup) {
        //获取登录的商家id
      String sellerId =  SecurityContextHolder.getContext().getAuthentication().getName();
      goodsGroup.getGoods().setSellerId(sellerId);
      try {
          goodsService.addGoods(goodsGroup);
          return new LLResult(true, "添加成功");
      }catch (Exception e) {
          e.printStackTrace();
          return new LLResult(false, "添加失败");
      }
    }
}
