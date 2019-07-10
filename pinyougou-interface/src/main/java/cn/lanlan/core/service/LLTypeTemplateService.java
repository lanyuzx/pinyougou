package cn.lanlan.core.service;

import cn.lanlan.core.pojo.entity.LLPageResult;
import cn.lanlan.core.pojo.entity.LLResult;
import cn.lanlan.core.pojo.template.TypeTemplate;

public interface LLTypeTemplateService {

    LLPageResult searchTypeTemplate(Integer page, Integer rows , TypeTemplate typeTemplate);

    void saveTypeTemplat(TypeTemplate typeTemplate);

    TypeTemplate findOneTypeTemplateById(Long id);

    void deleteTypeTemplateByIds(Long[] ids);
}
