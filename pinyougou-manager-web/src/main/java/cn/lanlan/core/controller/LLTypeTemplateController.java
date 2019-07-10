package cn.lanlan.core.controller;

import cn.lanlan.core.pojo.entity.LLPageResult;
import cn.lanlan.core.pojo.entity.LLResult;
import cn.lanlan.core.pojo.template.TypeTemplate;
import cn.lanlan.core.service.LLTypeTemplateService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


@RestController
@RequestMapping("/typeTemplate")
public class LLTypeTemplateController {

    @Reference
    LLTypeTemplateService typeTemplateService;


    @RequestMapping("/search")
    public LLPageResult searchTypeTemplate(Integer page, Integer rows, @RequestBody(required = false) TypeTemplate typeTemplate) {

        return typeTemplateService.searchTypeTemplate(page, rows, typeTemplate);
    }

    @RequestMapping("/add")
    public LLResult saveTypeTemplate( @RequestBody TypeTemplate typeTemplate) {
        try {

            typeTemplateService.saveTypeTemplat(typeTemplate);
            return new LLResult(true, "插入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new LLResult(false, "插入失败");
        }

    }
    @RequestMapping("/update")
    public LLResult updayeTypeTemplate(@RequestBody TypeTemplate typeTemplate) {

        return saveTypeTemplate(typeTemplate);
    }

    @RequestMapping("/findOne")
    public TypeTemplate findOne(Long id) {
        TypeTemplate typeTemplate =  typeTemplateService.findOneTypeTemplateById(id);
        return typeTemplate;
    }

    @RequestMapping("/delete")
    public LLResult deleteTypeTemplateByIds(Long[] ids) {
        try {
            typeTemplateService.deleteTypeTemplateByIds(ids);
            return new LLResult(true, "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new LLResult(false, "删除失败");
        }
    }

}
