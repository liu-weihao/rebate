package com.dx.ss.data.rebate.service;

import com.dx.ss.data.rebate.bo.SessionUser;
import com.dx.ss.data.rebate.dal.beans.UserInfo;
import com.dx.ss.data.rebate.dal.mapper.UserInfoMapper;
import com.dx.ss.data.rebate.enums.StatusCode;
import com.dx.ss.data.rebate.vo.ResponseObj;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public ResponseObj login(String username, String password) {


        Example ex = new Example(UserInfo.class);
        ex.createCriteria().andEqualTo("username", username);
        List<UserInfo> userList = userInfoMapper.selectByExample(ex);
        if (CollectionUtils.isEmpty(userList)) {
            return ResponseObj.fail(StatusCode.BIZ_FAILED, "登录失败，请核对账号或密码");
        }
        UserInfo userInfo = userList.get(0);
        if (!password.equals(userInfo.getPassword())) {
            return ResponseObj.fail(StatusCode.BIZ_FAILED, "登录失败，请核对账号或密码");
        }
        if (!userInfo.getStatus().equals(10)) {
            return ResponseObj.fail(StatusCode.BIZ_FAILED, "该账号已被禁用，请联系管理员");
        }
        SessionUser sessionUser = new SessionUser();
        sessionUser.setUserId(userInfo.getUserId());
        sessionUser.setUsername(username);
        sessionUser.setLoginTime(new Date());
        sessionUser.setStatus(userInfo.getStatus());
        return ResponseObj.success(sessionUser);
    }

}
