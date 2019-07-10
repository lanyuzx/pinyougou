package cn.lanlan.core.service.impl;

import cn.lanlan.core.mapper.ItemCatDao;
import cn.lanlan.core.pojo.item.ItemCat;

import cn.lanlan.core.pojo.item.ItemCatQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lanlan.core.service.LLItemCatService;

import java.util.List;

@Service
@Transactional
public class LLItemCatServiceImpl implements LLItemCatService {
    @Autowired
    private ItemCatDao itemCatDao;
    @Override
    public List<ItemCat> getItemCatListByParentId(String parentId) {

        ItemCatQuery itemCatQuery = new ItemCatQuery();
       ItemCatQuery.Criteria criteria = itemCatQuery.createCriteria();
       criteria.andParentIdEqualTo(Long.parseLong(parentId));
       return  itemCatDao.selectByExample(itemCatQuery);


    }

    @Override
    public void addOrUpdateItemCat(ItemCat itemCat) {
      if (itemCat.getId() != null) {
          itemCatDao.updateByPrimaryKeySelective(itemCat);
      }else  {
          itemCatDao.insert(itemCat);
      }
    }


}
