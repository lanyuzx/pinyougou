package cn.lanlan.core.service.impl;
import cn.lanlan.core.mapper.BrandDao;
import cn.lanlan.core.pojo.entity.LLPageResult;
import cn.lanlan.core.pojo.entity.LLResult;
import cn.lanlan.core.pojo.good.Brand;
import cn.lanlan.core.pojo.good.BrandQuery;
import cn.lanlan.core.service.LLBrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LLBrandServiceImpl implements LLBrandService {
    @Autowired
    private BrandDao brandDao;
    @Override
    public List<Brand> findAllBrand() {
        List<Brand> brandList = brandDao.selectByExample(null);

        return brandList;
    }

    @Override
    public LLPageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Page<Brand> brandPage = (Page<Brand>) brandDao.selectByExample(null);

        return new LLPageResult(brandPage.getTotal(),brandPage.getResult());
    }

    @Override
    public LLResult addBrand(Brand brand) {
        try {
            brandDao.insert(brand);
            return new LLResult(true,"添加成功");
        }catch (Exception e) {
            return new LLResult(false,"添加失败");
        }
    }

    @Override
    public Brand findBrandOne(Long id) {
        return brandDao.selectByPrimaryKey(id);
    }

    @Override
    public LLResult updateBrand(Brand brand) {
        try {
            brandDao.updateByPrimaryKey(brand);
            return new LLResult(true,"更新成功");
        }catch (Exception e) {
            return new LLResult(false,"更新失败");
        }

    }

    @Override
    public LLResult deleteBrand(Long[] ids) {
        try {
            for (Long id : ids ) {
                brandDao.deleteByPrimaryKey(id);
            }
            return new LLResult(true,"删除成功");
        }catch (Exception e) {
            return new LLResult(false,"删除失败");
        }
    }

    @Override
    public LLPageResult searchBrandList(Integer pageNum, Integer pageSize, Brand brand) {
        PageHelper.startPage(pageNum, pageSize);
        BrandQuery brandQuery = new BrandQuery();
        if (brand != null) {
           BrandQuery.Criteria criteria = brandQuery.createCriteria();
           if (StringUtils.isNotBlank(brand.getName())) {
               criteria.andNameLike("%" + brand.getName().trim() + "%");
           }
           if (StringUtils.isNotBlank(brand.getFirstChar())) {
               criteria.andFirstCharEqualTo(brand.getFirstChar().trim());
           }
        }
       Page<Brand> page = (Page<Brand>) brandDao.selectByExample(brandQuery);
        return new LLPageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<Map> selectOptionList() {
        return brandDao.selectOptionList();
    }
}
