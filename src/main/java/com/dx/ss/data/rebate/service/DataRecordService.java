package com.dx.ss.data.rebate.service;

import com.dx.ss.data.rebate.dal.beans.DataRecord;
import com.dx.ss.data.rebate.dal.mapper.DataRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataRecordService {

    @Autowired
    private DataRecordMapper dataRecordMapper;


    public boolean saveDataRecord(DataRecord dataRecord) {
        if(dataRecord == null) return false;
        if (dataRecord.getId() == null) {
            return dataRecordMapper.insertSelective(dataRecord) == 1;
        } else {
            return dataRecordMapper.updateByPrimaryKeySelective(dataRecord) == 1;
        }
    }
}
