package com.dx.ss.data.rebate.factory;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.dx.ss.data.rebate.pager.AppPager;
import com.dx.ss.data.rebate.pager.BasePager;
import org.springframework.stereotype.Component;

/**
 * Description:
 * 生成App端分页对象的工厂类
 * @author Eric Lau
 * @Date 2017/8/2.
 */
@Component("appPagerFactory")
public class AppPagerFactory implements PagerFactory {

    @Override
    public <E> BasePager<E> generatePager(Page<E> page) {
        PageInfo<E> pageInfo = new PageInfo<>(page);
        return new AppPager<>(pageInfo);
    }
}
