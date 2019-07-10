package cn.lanlan.core.service.impl;

import cn.lanlan.core.mapper.TypeTemplateDao;
import cn.lanlan.core.pojo.entity.LLPageResult;
import cn.lanlan.core.pojo.entity.LLResult;
import cn.lanlan.core.pojo.template.TypeTemplate;
import cn.lanlan.core.service.LLTypeTemplateService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LLTypeTemplateServiceImpl implements LLTypeTemplateService {
    @Autowired
    private TypeTemplateDao typeTemplateDao;
    @Override
    public LLPageResult searchTypeTemplate(Integer page, Integer rows, TypeTemplate typeTemplate) {
        PageHelper.startPage(page, rows);
       Page<TypeTemplate> typeTemplatePage = (Page<TypeTemplate>) typeTemplateDao.selectByExample(null);
        return new LLPageResult(typeTemplatePage.getTotal(), typeTemplatePage.getResult());
    }

    @Override
    public void saveTypeTemplat(TypeTemplate typeTemplate) {
        if (typeTemplate.getId() != null) {
            typeTemplateDao.updateByPrimaryKey(typeTemplate);
        }else  {
            typeTemplateDao.insert(typeTemplate);
        }

    }

    @Override
    public TypeTemplate findOneTypeTemplateById(Long id) {
        return typeTemplateDao.selectByPrimaryKey(id);
    }

    @Override
    public void deleteTypeTemplateByIds(Long[] ids) {
        for (long id: ids) {
            typeTemplateDao.deleteByPrimaryKey(id);
        }
    }
}
