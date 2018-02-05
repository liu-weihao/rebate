package com.dx.ss.data.rebate.dal.mapper;

import com.dx.ss.data.rebate.condition.search.UserListSearch;
import com.dx.ss.data.rebate.dal.beans.UserInfo;
import com.dx.ss.data.rebate.model.UserModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserInfoMapper extends Mapper<UserInfo> {

    List<UserModel> getUserList(@Param("search") UserListSearch search);
}