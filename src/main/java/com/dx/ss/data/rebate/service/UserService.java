package com.dx.ss.data.rebate.service;

import com.dx.ss.data.rebate.bo.SessionUser;
import com.dx.ss.data.rebate.condition.search.UserListSearch;
import com.dx.ss.data.rebate.dal.beans.UserInfo;
import com.dx.ss.data.rebate.dal.mapper.UserInfoMapper;
import com.dx.ss.data.rebate.enums.StatusCode;
import com.dx.ss.data.rebate.factory.PagerFactory;
import com.dx.ss.data.rebate.form.UserForm;
import com.dx.ss.data.rebate.pager.BasePager;
import com.dx.ss.data.rebate.utils.RandomUtils;
import com.dx.ss.data.rebate.vo.ResponseObj;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserInfoMapper userMapper;

    @Autowired
    private PagerFactory webPagerFactory;

    public ResponseObj login(String username, String password) {

        Example ex = new Example(UserInfo.class);
        ex.createCriteria().andEqualTo("username", username);
        List<UserInfo> userList = userMapper.selectByExample(ex);
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

    public UserInfo getUserInfo(String userId) {
        if (StringUtils.isBlank(userId)) return null;
        return userMapper.selectByPrimaryKey(userId);
    }

    public boolean addUserInfo(UserForm userForm) {
        UserInfo user = new UserInfo();
        BeanUtils.copyProperties(userForm, user);
        user.setUserId(RandomUtils.getPrimaryKey());
        user.setPassword(DigestUtils.md5Hex(userForm.getPassword()));
        user.setType(10);
        return userMapper.insertSelective(user) == 1;
    }

    public boolean editUserInfo(String userId, String name, Integer workAge, String phone) {
        if (StringUtils.isBlank(userId)) return false;
        UserInfo user = userMapper.selectByPrimaryKey(userId);
        if (user == null) return false;
        user.setName(name);
        user.setPhone(phone);
        if (workAge != null) {
            user.setWorkAge(workAge);
        }
        userMapper.updateByPrimaryKeySelective(user);
        return true;
    }

    public BasePager<UserInfo> getUserList(UserListSearch search) {
        Example ex = new Example(UserInfo.class);
        Example.Criteria criteria = ex.createCriteria();
        if (StringUtils.isNotBlank(search.getName())) {
            criteria.andEqualTo("name", search.getName());
        }
        if (StringUtils.isNotBlank(search.getPhone())) {
            criteria.andEqualTo("phone", search.getPhone());
        }
        PageHelper.startPage(search.getCPage(), search.getPSize(), "gmt_create DESC");
        return webPagerFactory.generatePager((Page<UserInfo>) userMapper.selectByExample(ex));
    }

    public boolean changePassword(String userId, String password) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(password)) return false;
        UserInfo user = userMapper.selectByPrimaryKey(userId);
        if (user == null) return false;
        user.setPassword(password);
        return userMapper.updateByPrimaryKeySelective(user) == 1;
    }

    public boolean changeUserStatus(String userId, Integer status) {
        if (StringUtils.isBlank(userId) || status == null) return false;
        UserInfo user = userMapper.selectByPrimaryKey(userId);

        if (user == null) return false;
        //超级管理员的状态不能更改
        if (user.getType().equals(0)) return false;

        user.setStatus(status);
        return userMapper.updateByPrimaryKeySelective(user) == 1;
    }
}
