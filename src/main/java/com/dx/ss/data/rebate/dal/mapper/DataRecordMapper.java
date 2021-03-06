package com.dx.ss.data.rebate.dal.mapper;

import com.dx.ss.data.rebate.condition.search.DataRecordSearch;
import com.dx.ss.data.rebate.dal.beans.DataRecord;
import com.dx.ss.data.rebate.mapper.SuperMapper;
import com.dx.ss.data.rebate.model.DataRecordModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataRecordMapper extends SuperMapper<DataRecord> {

    List<DataRecordModel> getDataRecordList(@Param("search") DataRecordSearch search);

    List<DataRecordModel> searchDataList(@Param("startTime") String startTime, @Param("endTime") String endTime);
}