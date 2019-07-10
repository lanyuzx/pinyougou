package cn.lanlan.core.service.impl;

import cn.lanlan.core.mapper.SellerDao;
import cn.lanlan.core.pojo.entity.LLPageResult;
import cn.lanlan.core.pojo.entity.LLResult;
import cn.lanlan.core.pojo.seller.Seller;
import cn.lanlan.core.pojo.seller.SellerQuery;
import cn.lanlan.core.service.LLSellerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class LLSellerServiceImpl implements LLSellerService {
    @Autowired
    private SellerDao sellerDao;
    @Override
    public LLResult addSeller(Seller seller) {

        try {
            //一进来肯定是未审核产品 0未审核  1审核
            seller.setStatus("0");
            seller.setCreateTime(new Date());
            sellerDao.insertSelective(seller);
            return new LLResult(true, "添加成功");

        }catch (Exception e) {
            e.printStackTrace();
            return new LLResult(false, "添加失败");
        }

    }

    @Override
    public LLPageResult  findSellerList(Integer page,Integer rows ,Seller seller) {
        PageHelper.startPage(page, rows);
        SellerQuery sellerQuery = new SellerQuery();
        SellerQuery.Criteria criteria = sellerQuery.createCriteria();
        if (seller != null) {
            if (StringUtils.isNotBlank(seller.getName())) {
                criteria.andNickNameEqualTo(seller.getName());
            }
            if (StringUtils.isNotBlank(seller.getNickName())) {
                criteria.andNickNameEqualTo(seller.getNickName());
            }
            if (StringUtils.isNotBlank(seller.getStatus())) {
                criteria.andStatusEqualTo(seller.getStatus());
            }
        }

        Page<Seller> sellerPage = (Page<Seller>) sellerDao.selectByExample(sellerQuery);
        return new LLPageResult(sellerPage.getTotal(), sellerPage.getResult());
    }

    @Override
    public Seller findSellerOneById(String sellerId) {
        return sellerDao.selectByPrimaryKey(sellerId);
    }

    @Override
    public void updateSellerStatus(String sellerId, String status) {
       Seller seller =  sellerDao.selectByPrimaryKey(sellerId);
       seller.setStatus(status);
       sellerDao.updateByPrimaryKey(seller);

    }
}
