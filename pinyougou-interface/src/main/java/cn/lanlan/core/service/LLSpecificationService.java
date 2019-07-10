package cn.lanlan.core.service;

import cn.lanlan.core.pojo.entity.LLPageResult;
import cn.lanlan.core.pojo.entity.LLResult;
import cn.lanlan.core.pojo.pojogroup.LLSecificationGroup;
import cn.lanlan.core.pojo.specification.Specification;

import java.util.List;
import java.util.Map;

public interface LLSpecificationService {
    LLPageResult serachSpecification(Integer page , Integer rows, Specification specification);

    List<Map>  selectOptionList();

    void insertSpecification(LLSecificationGroup secificationGroup);

    void updateSpecification(LLSecificationGroup secificationGroup);

    LLSecificationGroup findSpecificationGroupById(Long id);

    void deleteSecification(Long[] ids);

}
