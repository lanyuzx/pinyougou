package cn.lanlan.core.controller;

import cn.lanlan.core.pojo.entity.LLPageResult;
import cn.lanlan.core.pojo.entity.LLResult;
import cn.lanlan.core.pojo.good.Brand;
import cn.lanlan.core.service.LLBrandService;
import com.alibaba.dubbo.config.annotation.Reference;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class LLBrandController {
    @Reference
    private LLBrandService brandService;

    //查询所有品牌
    @RequestMapping("/findAll")
    public List<Brand> findAll() {
        List<Brand> brandList = brandService.findAllBrand();
        return brandList;
    }
    //分页查询品牌
    @RequestMapping("/findPage")
    public LLPageResult findPage(int page,int rows) {
        return brandService.findPage(page, rows);
    }
   //添加品牌
    @RequestMapping("/add")
    public LLResult addBrand(@RequestBody Brand brand) {
        LLResult result = brandService.addBrand(brand);
       return result;
    }
    //更新品牌
    @RequestMapping("/update")
    public LLResult updateBrand(@RequestBody Brand brand) {
        return brandService.updateBrand(brand);
    }
   //查询单个品牌
    @RequestMapping("/findOne")
    public Brand findBrandOne(Long id) {
        return brandService.findBrandOne(id);
    }

    //删除商品
    @RequestMapping("/delete")
    public LLResult deleteBrand(Long[] ids) {
        return brandService.deleteBrand(ids);
    }

    @RequestMapping("/search")
    public LLPageResult searchBrand(Integer pageNum,Integer pageSize,@RequestBody(required = false) Brand brand){
        return brandService.searchBrandList(pageNum, pageSize, brand);
    }

    //查询品牌数据
    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList() {
        List<Map> mapList = brandService.selectOptionList();
        return mapList;
    }




}


