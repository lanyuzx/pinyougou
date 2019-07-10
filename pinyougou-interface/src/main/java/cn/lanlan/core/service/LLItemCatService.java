package cn.lanlan.core.service;

import cn.lanlan.core.pojo.item.ItemCat;

import java.util.List;

public interface LLItemCatService {

    List<ItemCat> getItemCatListByParentId(String parentId);

    void  addOrUpdateItemCat(ItemCat itemCat);
}
