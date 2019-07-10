package cn.lanlan.core.pojo.entity;

import java.io.Serializable;
import java.util.List;

public class LLPageResult  implements Serializable {
    //总页数
    private Long total;
    //当前页的结果
    private List  rows;

    public LLPageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
