package com.dx.ss.data.rebate.controller;

import com.dx.ss.data.rebate.bo.Money;
import com.dx.ss.data.rebate.dal.beans.SalarySetting;
import com.dx.ss.data.rebate.enums.StatusCode;
import com.dx.ss.data.rebate.form.SalaryForm;
import com.dx.ss.data.rebate.service.SalaryService;
import com.dx.ss.data.rebate.vo.GridObj;
import com.dx.ss.data.rebate.vo.ResponseObj;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/web/salary")
public class SalaryController extends BaseController {

    @Autowired
    private SalaryService salaryService;

    @RequestMapping(value = "/list.web", produces = {"application/json;charset=UTF-8"})
    public GridObj<SalarySetting> getSalaryList(String type) {
        return GridObj.of(salaryService.getSalarySettings(type));
    }


    @RequestMapping(value = "/saveSalarySetting.web", produces = {"application/json;charset=UTF-8"})
    public ResponseObj getSalaryList(@Valid SalaryForm salaryForm, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseObj.fail(StatusCode.FORM_INVALID, result.getAllErrors().get(0).getDefaultMessage());
        }
        SalarySetting setting = new SalarySetting();
        Money percent = new Money(salaryForm.getPercent());
        percent.divideBy(100);
        BeanUtils.copyProperties(salaryForm, setting);
        setting.setPercent(percent.getAmount());
        ResponseObj obj = salaryService.isValid(setting);
        if (!obj.isSuccess()) return obj;
        if (salaryService.saveSalarySetting(setting)) {
            return ResponseObj.success();
        }
        return ResponseObj.fail();
    }


}
