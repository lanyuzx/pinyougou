package cn.lanlan.core.controller;

import cn.lanlan.core.pojo.entity.LLResult;
import cn.lanlan.core.pojo.item.ItemCat;
import cn.lanlan.core.service.LLItemCatService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itemCat")
public class LLItemCatController {

    @Reference
    private LLItemCatService itemCatService;
    @RequestMapping("/findByParentId")
    public List<ItemCat> findByParentId(String parentId) {
        return itemCatService.getItemCatListByParentId(parentId);
    }

    @RequestMapping("/add")
    public LLResult addItemCat(@RequestBody ItemCat itemCat)
    {
      try {
          itemCatService.addOrUpdateItemCat(itemCat);
          return  new LLResult(true, "添加成功");
      }catch (Exception e) {
          return  new LLResult(false, "添加失败");
      }

    }

}
