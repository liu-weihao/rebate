package com.dx.ss.data.rebate.service;

import com.dx.ss.data.rebate.condition.search.AccountSearch;
import com.dx.ss.data.rebate.dal.beans.Account;
import com.dx.ss.data.rebate.dal.beans.UserAccount;
import com.dx.ss.data.rebate.dal.mapper.AccountMapper;
import com.dx.ss.data.rebate.dal.mapper.UserAccountMapper;
import com.dx.ss.data.rebate.factory.PagerFactory;
import com.dx.ss.data.rebate.form.AccountForm;
import com.dx.ss.data.rebate.form.AssignForm;
import com.dx.ss.data.rebate.model.UserAccountModel;
import com.dx.ss.data.rebate.pager.BasePager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public boolean assign(AssignForm assignForm) {
        UserAccount userAccount = new UserAccount();
        BeanUtils.copyProperties(assignForm, userAccount);
        return userAccountMapper.insertSelective(userAccount) == 1;
    }

    public BasePager<Account> getAccountList(AccountSearch search) {
        Example ex = new Example(Account.class);
        PageHelper.startPage(search.getCPage(), search.getPSize(), "gmt_create DESC");
        return webPagerFactory.generatePager((Page<Account>) accountMapper.selectByExample(ex));
    }

    public BasePager<UserAccountModel> getUserAccountList(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        return webPagerFactory.generatePager((Page<UserAccountModel>) userAccountMapper.getUserAccountList());
    }

    public List<UserAccountModel> getUserAccountList(String startTime, String endTime) {
        List<UserAccountModel> dataList = new ArrayList<>();
        List<UserAccountModel> accounts = userAccountMapper.getUserAccountList().stream().filter(account -> StringUtils.isNotBlank(account.getUserId())).collect(Collectors.toList());
        DateTime start = DateTime.parse(startTime);
        DateTime end = DateTime.parse(endTime);
        int days = Days.daysBetween(start, end).getDays() + 1;
        for (int i = 0; i < days; i++) {
            for (UserAccountModel model : accounts) {
                UserAccountModel copy = new UserAccountModel();
                BeanUtils.copyProperties(model, copy);
                copy.setGmtCreate(start.toDate());
                dataList.add(copy);
            }
            dataList.add(new UserAccountModel());
            start = start.plusDays(1);
        }
        return dataList;
    }

    public List<Account> getAccountByUser(String userId) {
        Example example = new Example(UserAccount.class);
        example.createCriteria().andEqualTo("userId", userId);
        List<UserAccount> userAccounts = userAccountMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userAccounts)) return Lists.newArrayList();
        List<Integer> accountIds = new ArrayList<>(userAccounts.size());
        userAccounts.forEach(userAccount -> accountIds.add(userAccount.getAccountId()));
        Example ex = new Example(Account.class);
        ex.createCriteria().andIn("id", accountIds);
        return accountMapper.selectByExample(ex);
    }

    public boolean changeAccountUser(Integer id, String userId, Integer accountId) {
        if (id == null || accountId == null || StringUtils.isBlank(userId)) return false;
        UserAccount userAccount = userAccountMapper.selectByPrimaryKey(id);
        if (userAccount == null) return false;
        userAccount.setUserId(userId);
        userAccount.setAccountId(accountId);
        return userAccountMapper.updateByPrimaryKeySelective(userAccount) == 1;
    }

    public boolean deleteAccountUser(Integer id) {
        return id != null && userAccountMapper.deleteByPrimaryKey(id) == 1;
    }
}
