package cn.lanlan.core.pojo.pojogroup;

import cn.lanlan.core.pojo.specification.Specification;
import cn.lanlan.core.pojo.specification.SpecificationOption;

import java.io.Serializable;
import java.util.List;

public class LLSecificationGroup implements Serializable {

    private Specification specification;

    List<SpecificationOption >  specificationOptionList;

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public List<SpecificationOption> getSpecificationOptionList() {
        return specificationOptionList;
    }

    public void setSpecificationOptionList(List<SpecificationOption> specificationOptionList) {
        this.specificationOptionList = specificationOptionList;
    }





}
