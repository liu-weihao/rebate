package com.dx.ss.data.rebate.controller;

import com.dx.ss.data.rebate.condition.search.AccountSearch;
import com.dx.ss.data.rebate.dal.beans.Account;
import com.dx.ss.data.rebate.enums.StatusCode;
import com.dx.ss.data.rebate.form.AccountForm;
import com.dx.ss.data.rebate.pager.BasePager;
import com.dx.ss.data.rebate.pager.WebPager;
import com.dx.ss.data.rebate.service.AccountService;
import com.dx.ss.data.rebate.vo.GridObj;
import com.dx.ss.data.rebate.vo.ResponseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/web/account")
public class AccountController extends BaseController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/list.web", produces = { "application/json;charset=UTF-8" })
    public GridObj<Account> getAccountList(AccountSearch search) {

        BasePager<Account> accountList = accountService.getAccountList(search);
        return GridObj.of((WebPager<Account>) accountList);
    }

    @RequestMapping(value = "/addAccount.web", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
    public ResponseObj addAccount(@Valid AccountForm accountForm, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseObj.fail(StatusCode.FORM_INVALID, result.getAllErrors().get(0).getDefaultMessage());
        }
        if(accountService.addAccount(accountForm)) {
            return ResponseObj.success();
        }
        return ResponseObj.fail();
    }

}
