package cn.lanlan.core.controller;

import cn.lanlan.core.pojo.entity.LLPageResult;
import cn.lanlan.core.pojo.entity.LLResult;
import cn.lanlan.core.pojo.pojogroup.LLSecificationGroup;
import cn.lanlan.core.pojo.specification.Specification;
import cn.lanlan.core.service.LLSpecificationService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/specification/")
public class LLSpecificationController {
    @Reference
    LLSpecificationService specificationService;
    @RequestMapping("/search")
    public LLPageResult searchSpecification(Integer page, Integer rows , @RequestBody(required = false)Specification specification) {

        return  specificationService.serachSpecification(page, rows, specification);
    }

    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList() {
        return specificationService.selectOptionList();
    }

    @RequestMapping("/add")
    public LLResult addSpecification(@RequestBody LLSecificationGroup secificationGroup) {
        try {
            specificationService.insertSpecification(secificationGroup);
            return new LLResult(true,"添加成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new LLResult(false,"添加失败");
        }

    }
/*
修改商品
*
* */
    @RequestMapping("/findOne")
    public LLSecificationGroup findSpecificationGroupById(Long id) {
        return specificationService.findSpecificationGroupById(id);
    }

    @RequestMapping("/update")
    public LLResult updateSpecification(@RequestBody LLSecificationGroup secificationGroup) {
        try {
            specificationService.updateSpecification(secificationGroup);
            return new LLResult(true, "更新成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new LLResult(false, "更新失败");
        }
    }

    /*
    删除规格
    * */
    @RequestMapping("/delete")
    public LLResult deleteSpecification(Long[] ids) {
        try {
            specificationService.deleteSecification(ids);
            return new LLResult(true, "删除成功");
        }catch (Exception e) {
            return new LLResult(true, "删除失败");
        }
    }

}
