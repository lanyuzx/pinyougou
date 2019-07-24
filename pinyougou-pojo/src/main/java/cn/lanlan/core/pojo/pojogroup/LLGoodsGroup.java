package cn.lanlan.core.pojo.pojogroup;

import cn.lanlan.core.pojo.good.Goods;
import cn.lanlan.core.pojo.good.GoodsDesc;
import cn.lanlan.core.pojo.item.Item;

import java.io.Serializable;
import java.util.List;

public class LLGoodsGroup implements Serializable {

    private Goods goods;
    private GoodsDesc goodsDesc;

    private List<Item> itemList;

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public GoodsDesc getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(GoodsDesc goodsDesc) {
        this.goodsDesc = goodsDesc;
    }
}
