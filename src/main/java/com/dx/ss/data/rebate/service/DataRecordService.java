package com.dx.ss.data.rebate.service;

import com.dx.ss.data.rebate.condition.search.DataRecordSearch;
import com.dx.ss.data.rebate.dal.beans.DataRecord;
import com.dx.ss.data.rebate.dal.mapper.DataRecordMapper;
import com.dx.ss.data.rebate.model.DataRecordModel;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataRecordService {

    @Autowired
    private DataRecordMapper dataRecordMapper;


    public List<DataRecord> getDataList(DataRecordSearch search) {


        return dataRecordMapper.selectAll();
    }

    public boolean saveDataRecord(DataRecord dataRecord) {
        if(dataRecord == null) return false;
        if (dataRecord.getId() == null) {
            return dataRecordMapper.insertSelective(dataRecord) == 1;
        } else {
            return dataRecordMapper.updateByPrimaryKeySelective(dataRecord) == 1;
        }
    }

    public int saveDataRecords(List<DataRecord> dataList) {
        if(CollectionUtils.isEmpty(dataList)) return 0;
        return dataRecordMapper.insertList(dataList);
    }

    public List<DataRecordModel> getDataRecordList(DataRecordSearch search) {

        return dataRecordMapper.getDataRecordList(search);
    }

}
