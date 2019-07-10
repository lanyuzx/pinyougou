package cn.lanlan.core.service.impl;

import cn.lanlan.core.mapper.SpecificationDao;
import cn.lanlan.core.mapper.SpecificationOptionDao;
import cn.lanlan.core.pojo.entity.LLPageResult;
import cn.lanlan.core.pojo.pojogroup.LLSecificationGroup;
import cn.lanlan.core.pojo.specification.Specification;
import cn.lanlan.core.pojo.specification.SpecificationOption;
import cn.lanlan.core.pojo.specification.SpecificationOptionQuery;
import cn.lanlan.core.pojo.specification.SpecificationQuery;
import cn.lanlan.core.service.LLSpecificationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LLSpecificationServiceImpl implements LLSpecificationService {
    @Autowired
    SpecificationDao specificationDao;

    @Autowired
    SpecificationOptionDao specificationOptionDao;
    @Override
    public LLPageResult serachSpecification(Integer page, Integer rows, Specification specification) {

        PageHelper.startPage(page, rows);
        SpecificationQuery specificationQuery = new SpecificationQuery();
        if (specification != null) {
           SpecificationQuery.Criteria criteria = specificationQuery.createCriteria();
            if (StringUtils.isNotBlank(specification.getSpecName())) {
                criteria.andSpecNameEqualTo(specification.getSpecName());
            }
        }
      Page<Specification> specificationPage = (Page<Specification>) specificationDao.selectByExample(specificationQuery);

        return new LLPageResult(specificationPage.getTotal(), specificationPage.getResult());
    }

    @Override
    public List<Map> selectOptionList() {
        return specificationOptionDao.selectOptionList();
    }

    @Override
    public void insertSpecification(LLSecificationGroup secificationGroup) {
        specificationDao.insert(secificationGroup.getSpecification());
        Specification specification = secificationGroup.getSpecification();
        for (SpecificationOption specificationOption : secificationGroup.getSpecificationOptionList()) {
            specificationOption.setSpecId(specification.getId());
            specificationOptionDao.insert(specificationOption);
        }

    }

    @Override
    public void updateSpecification(LLSecificationGroup secificationGroup) {
        specificationDao.updateByPrimaryKey(secificationGroup.getSpecification());

        SpecificationOptionQuery query = new SpecificationOptionQuery();
        query.createCriteria().andSpecIdEqualTo(secificationGroup.getSpecification().getId());
        List<SpecificationOption> specificationOptionList = specificationOptionDao.selectByExample(query);
        for (SpecificationOption specificationOption : specificationOptionList) {
            specificationOptionDao.deleteByPrimaryKey(specificationOption.getId());
        }
        for (SpecificationOption specificationOption : secificationGroup.getSpecificationOptionList()) {
            specificationOption.setSpecId(secificationGroup.getSpecification().getId());
            specificationOptionDao.insert(specificationOption);
        }
    }

    @Override
    public LLSecificationGroup findSpecificationGroupById(Long id) {
        Specification specification = specificationDao.selectByPrimaryKey(id);
        SpecificationOptionQuery query = new SpecificationOptionQuery();
        SpecificationOptionQuery.Criteria criteria = query.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<SpecificationOption> specificationOptionList = specificationOptionDao.selectByExample(query);
        LLSecificationGroup secificationGroup = new LLSecificationGroup();
        secificationGroup.setSpecification(specification);
        secificationGroup.setSpecificationOptionList(specificationOptionList);
        return secificationGroup;
    }

    @Override
    public void deleteSecification(Long[] ids) {
        //先删除从表
        for (Long id : ids) {
            SpecificationOptionQuery query = new SpecificationOptionQuery();
            query.createCriteria().andSpecIdEqualTo(id);
            List<SpecificationOption> specificationOptionList = specificationOptionDao.selectByExample(query);
            for (SpecificationOption specificationOption : specificationOptionList) {
                specificationOptionDao.deleteByPrimaryKey(specificationOption.getId());
            }
            specificationDao.deleteByPrimaryKey(id);
        }
    }
}
