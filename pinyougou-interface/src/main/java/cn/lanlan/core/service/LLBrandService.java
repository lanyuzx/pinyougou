package cn.lanlan.core.service;

import cn.lanlan.core.pojo.entity.LLPageResult;
import cn.lanlan.core.pojo.entity.LLResult;
import cn.lanlan.core.pojo.good.Brand;

import java.util.List;
import java.util.Map;

public interface LLBrandService {
    //查询所有的品牌
    List<Brand> findAllBrand();
    //分页查询品牌
    LLPageResult findPage(Integer pageNum, Integer pageSize);
    //添加一个品牌
    LLResult addBrand(Brand brand);

    //查询单个品牌
    Brand findBrandOne(Long id);

    //修改单个品牌
    LLResult  updateBrand(Brand brand);
    //删除该品牌
    LLResult  deleteBrand(Long[] ids);
    //分页条件查询
    LLPageResult searchBrandList(Integer pageNum, Integer pageSize ,Brand brand);

    //品牌下拉框数据
    List<Map> selectOptionList();
}
