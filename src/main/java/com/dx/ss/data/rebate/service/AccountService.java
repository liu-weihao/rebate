package com.dx.ss.data.rebate.service;

import com.dx.ss.data.rebate.condition.search.AccountSearch;
import com.dx.ss.data.rebate.dal.beans.Account;
import com.dx.ss.data.rebate.dal.beans.UserAccount;
import com.dx.ss.data.rebate.dal.mapper.AccountMapper;
import com.dx.ss.data.rebate.dal.mapper.UserAccountMapper;
import com.dx.ss.data.rebate.factory.PagerFactory;
import com.dx.ss.data.rebate.form.AccountForm;
import com.dx.ss.data.rebate.model.UserAccountModel;
import com.dx.ss.data.rebate.pager.BasePager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private PagerFactory webPagerFactory;

    public boolean addAccount(AccountForm userForm) {
        Account user = new Account();
        BeanUtils.copyProperties(userForm, user);
        return accountMapper.insertSelective(user) == 1;
    }

    public boolean saveAccountInfo(AccountForm accountForm) {
        if (accountForm.getId() != null) {

            Account account = accountMapper.selectByPrimaryKey(accountForm.getId());
            if (account == null) return false;
            BeanUtils.copyProperties(accountForm, account);
            return accountMapper.updateByPrimaryKeySelective(account) == 1;
        } else {
            Account user = new Account();
            BeanUtils.copyProperties(accountForm, user);
            return accountMapper.insertSelective(user) == 1;
        }
    }

    public BasePager<Account> getAccountList(AccountSearch search) {
        Example ex = new Example(Account.class);
        PageHelper.startPage(search.getCPage(), search.getPSize(), "gmt_create DESC");
        return webPagerFactory.generatePager((Page<Account>) accountMapper.selectByExample(ex));
    }

    public List<UserAccountModel> getUserAccountList(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        return userAccountMapper.getUserAccountList();
    }

    public boolean changeAccountUser(Integer id, String userId, Integer accountId) {
        if(id == null || accountId == null || StringUtils.isBlank(userId)) return false;
        UserAccount userAccount = userAccountMapper.selectByPrimaryKey(id);
        if(userAccount == null) return false;
        userAccount.setUserId(userId);
        userAccount.setAccountId(accountId);
        return userAccountMapper.updateByPrimaryKeySelective(userAccount) == 1;
    }

    public boolean deleteAccountUser(Integer id) {
        return id != null && userAccountMapper.deleteByPrimaryKey(id) == 1;
    }
}
