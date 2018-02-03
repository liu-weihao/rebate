package com.dx.ss.data.rebate.controller;

import com.dx.ss.data.rebate.condition.search.DataRecordSearch;
import com.dx.ss.data.rebate.dal.beans.DataRecord;
import com.dx.ss.data.rebate.enums.StatusCode;
import com.dx.ss.data.rebate.form.DataForm;
import com.dx.ss.data.rebate.service.DataRecordService;
import com.dx.ss.data.rebate.vo.ResponseObj;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/web/data")
public class DataController extends BaseController {

    @Autowired
    private DataRecordService dataRecordService;


    @RequestMapping(value = "/list.web", produces = {"application/json;charset=UTF-8"})
    public ResponseObj getDataRecordList(DataRecordSearch search) {
        return ResponseObj.success(dataRecordService.getDataRecordList(search));
    }

    @RequestMapping(value = "/searchDataList.web", produces = {"application/json;charset=UTF-8"})
    public ResponseObj searchDataList(@RequestParam(name = "startTime") String startTime, @RequestParam(name = "endTime") String endTime) {

        return ResponseObj.success(dataRecordService.searchDataList(startTime, endTime));
    }

    @RequestMapping(value = "/record.web", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public ResponseObj record(@Valid DataForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseObj.fail(StatusCode.FORM_INVALID, result.getAllErrors().get(0).getDefaultMessage());
        }
        DataRecord record = new DataRecord();
        BeanUtils.copyProperties(form, record);
        return ResponseObj.success(dataRecordService.saveDataRecord(record));
    }
}
