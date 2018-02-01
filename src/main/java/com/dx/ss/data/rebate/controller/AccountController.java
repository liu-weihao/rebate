package com.dx.ss.data.rebate.controller;

import com.dx.ss.data.rebate.enums.StatusCode;
import com.dx.ss.data.rebate.form.AccountForm;
import com.dx.ss.data.rebate.form.AssignForm;
import com.dx.ss.data.rebate.model.UserAccountModel;
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

    @RequestMapping(value = "/list.web", produces = {"application/json;charset=UTF-8"})
    public GridObj<UserAccountModel> getAccountList(Integer cPage, Integer pSize) {

        BasePager<UserAccountModel> accountList = accountService.getUserAccountList(cPage, pSize);
        return GridObj.of((WebPager<UserAccountModel>) accountList);
    }

    @RequestMapping(value = "/saveAccount.web", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public ResponseObj saveAccount(@Valid AccountForm accountForm, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseObj.fail(StatusCode.FORM_INVALID, result.getAllErrors().get(0).getDefaultMessage());
        }
        if (accountService.saveAccountInfo(accountForm)) {
            return ResponseObj.success();
        }
        return ResponseObj.fail();
    }

    @RequestMapping(value = "/assign.web", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public ResponseObj assign(@Valid AssignForm assignForm, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseObj.fail(StatusCode.FORM_INVALID, result.getAllErrors().get(0).getDefaultMessage());
        }
        if (accountService.assign(assignForm)) {
            return ResponseObj.success();
        }
        return ResponseObj.fail();
    }
}
