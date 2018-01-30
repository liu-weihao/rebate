package com.dx.ss.data.rebate.dao;

import com.dx.ss.data.rebate.mapper.SuperMapper;

import java.util.List;

public interface IBatchDao<M extends SuperMapper<T>, T> extends IDao<M, T>{

    int insertList(List<T> dataList);
}
