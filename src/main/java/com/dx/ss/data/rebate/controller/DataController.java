package com.dx.ss.data.rebate.controller;

import com.dx.ss.data.rebate.condition.search.DataRecordSearch;
import com.dx.ss.data.rebate.service.DataRecordService;
import com.dx.ss.data.rebate.vo.ResponseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/web/data")
public class DataController extends BaseController {

    @Autowired
    private DataRecordService dataRecordService;


    @RequestMapping(value = "/list.web", produces = {"application/json;charset=UTF-8"})
    public ResponseObj getDataRecordList(DataRecordSearch search) {
        return ResponseObj.success(dataRecordService.getDataRecordList(search));
    }
}
