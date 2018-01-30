package com.dx.ss.data.rebate.service;

import com.dx.ss.data.rebate.dal.beans.SalarySetting;
import com.dx.ss.data.rebate.dal.mapper.SalarySettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryService {

    @Autowired
    private SalarySettingMapper salaryMapper;

    public List<SalarySetting> getSalarySettings() {
        return salaryMapper.selectAll();
    }


    public boolean saveSalarySetting(SalarySetting setting) {
        if (setting == null) return false;

        if (setting.getId() == null) {
            return salaryMapper.insertSelective(setting) == 1;
        } else {
            return salaryMapper.updateByPrimaryKeySelective(setting) == 1;
        }
    }

}
