package cn.lanlan.core.service.impl;

import cn.lanlan.core.mapper.GoodsDao;
import cn.lanlan.core.mapper.GoodsDescDao;
import cn.lanlan.core.pojo.pojogroup.LLGoodsGroup;
import cn.lanlan.core.service.LLGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LLGoodsServiceImpl implements LLGoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsDescDao goodsDescDao;
    @Override
    public void addGoods(LLGoodsGroup goodsGroup) {

        goodsGroup.getGoods().setAuditStatus("0");
        goodsDao.insert(goodsGroup.getGoods());

        goodsGroup.getGoodsDesc().setGoodsId(goodsGroup.getGoods().getId());
        goodsDescDao.insert(goodsGroup.getGoodsDesc());

    }
}
