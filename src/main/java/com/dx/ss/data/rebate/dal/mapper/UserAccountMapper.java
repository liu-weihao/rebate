package com.dx.ss.data.rebate.dal.mapper;

import com.dx.ss.data.rebate.dal.beans.UserAccount;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserAccountMapper extends Mapper<UserAccount> {

    List<UserAccountMapper> getUserAccountList();
}