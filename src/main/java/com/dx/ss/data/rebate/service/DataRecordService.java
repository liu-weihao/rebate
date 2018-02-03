package com.dx.ss.data.rebate.service;

import com.dx.ss.data.rebate.condition.search.DataRecordSearch;
import com.dx.ss.data.rebate.dal.beans.DataRecord;
import com.dx.ss.data.rebate.dal.mapper.DataRecordMapper;
import com.dx.ss.data.rebate.dal.mapper.UserAccountMapper;
import com.dx.ss.data.rebate.model.DataRecordModel;
import com.dx.ss.data.rebate.model.UserAccountModel;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataRecordService {

    @Autowired
    private DataRecordMapper dataRecordMapper;

    @Autowired
    private UserAccountMapper accountMapper;

    public List<DataRecord> getDataList(DataRecordSearch search) {


        return dataRecordMapper.selectAll();
    }

    public List<DataRecordModel> searchDataList(String startTime, String endTime) {

        List<DataRecordModel> resultList = new ArrayList<>();

        Example example = new Example(DataRecord.class);
        example.setOrderByClause("record_date ASC");
        example.createCriteria().andGreaterThanOrEqualTo("recordDate", startTime).andLessThanOrEqualTo("recordDate", endTime);
        List<DataRecord> dataList = dataRecordMapper.selectByExample(example);

        List<UserAccountModel> accountList = accountMapper.getAccountList();
        DateTime start = DateTime.parse(startTime);
        DateTime end = DateTime.parse(endTime);
        int days = Days.daysBetween(start, end).getDays() + 1;
        for (int i = 0; i < days; i++) {
            for (UserAccountModel account : accountList) {
                DataRecord record = null;
                for (DataRecord r : dataList) {
                    if (r.getAccountId().equals(account.getAccountId()) && r.getRecordDate().equals(start.toDate())) {
                        record = r;
                        break;
                    }
                }
                DataRecordModel copy = new DataRecordModel();
                if (record == null) { //当天没有录入数据
                    record = new DataRecord();
                }
                BeanUtils.copyProperties(record, copy);
                BeanUtils.copyProperties(account, copy);
                copy.setRecordDate(start.toDate());
                resultList.add(copy);
            }
            if (i != days - 1) resultList.add(new DataRecordModel()); //相当于塞一个空行
            start = start.plusDays(1);
        }
        return resultList;
    }

    public DataRecord saveDataRecord(DataRecord dataRecord) {
        if (dataRecord == null) return null;
        if (dataRecord.getId() != null && dataRecord.getId() > 0) {
            dataRecordMapper.updateByPrimaryKeySelective(dataRecord);
        } else {
            dataRecordMapper.insertSelective(dataRecord);
        }
        return dataRecord;
    }

    public int saveDataRecords(List<DataRecord> dataList) {
        if (CollectionUtils.isEmpty(dataList)) return 0;
        return dataRecordMapper.insertList(dataList);
    }

    public Map<Integer, List<DataRecordModel>> getDataRecordList(DataRecordSearch search) {

        //因为要算增量，所以时间倒退一天
        DateTime start = new DateTime(search.getStartTime().getTime()).minusDays(1);
        DateTime end = new DateTime(search.getEndTime().getTime());
        search.setStartTime(start.toDate());


        List<DataRecordModel> recordList = dataRecordMapper.getDataRecordList(search);
        if (CollectionUtils.isEmpty(recordList)) return Maps.newHashMap();

        Map<Integer, List<DataRecordModel>> map = new HashMap<>();
        int days = Days.daysBetween(start, end).getDays() + 1;
        for (int i = 0; i < days; i++) {
            Date date = start.toDate();
            map.put(i, recordList.stream().filter(record -> date.equals(record.getRecordDate())).collect(Collectors.toList()));
            start = start.plusDays(1);
        }
        return map;
    }

}
