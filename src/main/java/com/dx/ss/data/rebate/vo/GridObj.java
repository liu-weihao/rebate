package com.dx.ss.data.rebate.vo;

import com.dx.ss.data.rebate.pager.WebPager;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GridObj<T> {

    private List<T> dataList;

    private long total;

    public static <T> GridObj<T> of(WebPager<T> pager) {
        GridObj<T> grid = new GridObj<>();
        grid.setDataList(pager.getDataList());
        grid.setTotal(pager.getTotal());
        return grid;
    }
}
