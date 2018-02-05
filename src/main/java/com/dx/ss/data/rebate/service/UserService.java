package com.dx.ss.data.rebate.service;

import com.dx.ss.data.rebate.bo.SessionUser;
import com.dx.ss.data.rebate.condition.search.UserListSearch;
import com.dx.ss.data.rebate.dal.beans.UserAccount;
import com.dx.ss.data.rebate.dal.beans.UserInfo;
import com.dx.ss.data.rebate.dal.beans.UserRoleInfo;
import com.dx.ss.data.rebate.dal.mapper.UserAccountMapper;
import com.dx.ss.data.rebate.dal.mapper.UserInfoMapper;
import com.dx.ss.data.rebate.dal.mapper.UserRoleInfoMapper;
import com.dx.ss.data.rebate.enums.StatusCode;
import com.dx.ss.data.rebate.factory.PagerFactory;
import com.dx.ss.data.rebate.form.UserForm;
import com.dx.ss.data.rebate.model.UserModel;
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
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserInfoMapper userMapper;

    @Autowired
    private UserRoleInfoMapper userRoleInfoMapper;

    @Autowired
    private UserAccountMapper userAccountMapper;

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
        UserRoleInfo probe = new UserRoleInfo();
        probe.setUserId(userInfo.getUserId());

        UserRoleInfo userRoleInfo = userRoleInfoMapper.selectOne(probe);
        SessionUser sessionUser = new SessionUser();
        sessionUser.setUserId(userInfo.getUserId());
        sessionUser.setRoleId(userRoleInfo.getRoleId());
        sessionUser.setUsername(username);
        sessionUser.setLoginTime(new Date());
        sessionUser.setStatus(userInfo.getStatus());
        return ResponseObj.success(sessionUser);
    }

    public UserInfo getUserInfo(String userId) {
        if (StringUtils.isBlank(userId)) return null;
        return userMapper.selectByPrimaryKey(userId);
    }

    public List<UserInfo> getUserByUsername(String username) {
        if (StringUtils.isBlank(username)) return null;
        Example ex = new Example(UserInfo.class);
        ex.createCriteria().andEqualTo("username", username);
        return userMapper.selectByExample(ex);
    }

    @Transactional
    public boolean addUserInfo(UserForm userForm) {
        UserInfo user = new UserInfo();
        BeanUtils.copyProperties(userForm, user);
        user.setUserId(RandomUtils.getPrimaryKey());
        user.setPassword(DigestUtils.md5Hex(userForm.getPassword()));
        user.setNickname(userForm.getName());
        user.setType(10);
        UserRoleInfo userRole = new UserRoleInfo();
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(userForm.getRoleId());
        userRoleInfoMapper.insertSelective(userRole);
        return userMapper.insertSelective(user) == 1;
    }

    public boolean editUserInfo(String userId, String name, Integer workAge, String phone, Integer roleId) {
        if (StringUtils.isBlank(userId)) return false;
        UserInfo user = userMapper.selectByPrimaryKey(userId);
        if (user == null) return false;
        user.setName(name);
        user.setNickname(name);
        user.setPhone(phone);
        if (workAge != null) {
            user.setWorkAge(workAge);
        }
        Example ex = new Example(UserInfo.class);
        ex.createCriteria().andEqualTo("userId", userId);
        userRoleInfoMapper.deleteByExample(ex); //删除角色关联

        UserRoleInfo userRole = new UserRoleInfo();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRoleInfoMapper.insertSelective(userRole); //重新关联角色
        userMapper.updateByPrimaryKeySelective(user);
        return true;
    }

    public BasePager<UserModel> getUserList(UserListSearch search) {
        PageHelper.startPage(search.getCPage(), search.getPSize(), "gmt_create DESC");
        return webPagerFactory.generatePager((Page<UserModel>) userMapper.getUserList(search));
    }

    public List<UserInfo> getUserList() {
        return userMapper.selectAll();
    }

    public List<UserInfo> getUnassignedUsers() {
        List<UserAccount> userAccountList = userAccountMapper.selectAll();
        List<String> userIds = new ArrayList<>(userAccountList.size());
        userAccountList.forEach(account -> userIds.add(account.getUserId()));
        Example ex = new Example(UserInfo.class);
        ex.createCriteria().andNotIn("userId", userIds);
        return userMapper.selectByExample(ex);
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
