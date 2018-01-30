package com.dx.ss.data.rebate.pager;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Description:
 * 分页对象抽象类。
 * @author Eric Lau
 * @Date 2017/8/2.
 */
@Getter
@Setter
public abstract class BasePager<E> {

    private int pageNum;

    private int pageSize;

    private List<E> dataList;

    public BasePager() {
    }

    public BasePager(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

}
