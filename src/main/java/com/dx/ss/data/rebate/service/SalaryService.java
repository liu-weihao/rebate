package com.dx.ss.data.rebate.service;

import com.dx.ss.data.rebate.bo.NumberSection;
import com.dx.ss.data.rebate.dal.beans.SalarySetting;
import com.dx.ss.data.rebate.dal.mapper.SalarySettingMapper;
import com.dx.ss.data.rebate.enums.StatusCode;
import com.dx.ss.data.rebate.vo.ResponseObj;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SalaryService {

    @Autowired
    private SalarySettingMapper salaryMapper;

    public List<SalarySetting> getSalarySettings(String type) {

        Example ex = new Example(SalarySetting.class);
        ex.setOrderByClause("type ASC, start ASC");
        if (StringUtils.isNotBlank(type)) {
            ex.createCriteria().andEqualTo("type", type);
        }
        return salaryMapper.selectByExample(ex);
    }


    public boolean saveSalarySetting(SalarySetting setting) {
        if (setting == null) return false;

        if (setting.getId() == null) {
            return salaryMapper.insertSelective(setting) == 1;
        } else {
            return salaryMapper.updateByPrimaryKeySelective(setting) == 1;
        }
    }

    public boolean removeSalary(Integer id) {
        return id != null && id > 0 && salaryMapper.deleteByPrimaryKey(id) == 1;
    }

    public ResponseObj isValid(SalarySetting setting) {

        List<SalarySetting> settings = getSalarySettings(setting.getType());
        if (CollectionUtils.isEmpty(settings)) return ResponseObj.success();
        NumberSection sectionA = new NumberSection(setting.getStart(), setting.getEnd());
        boolean isEdit = setting.getId() != null && setting.getId() > 0;
        for (SalarySetting s : settings) {
            NumberSection sectionB = new NumberSection(s.getStart(), s.getEnd());
            if (isEdit && s.getId().equals(setting.getId())) continue;
            boolean isInclude = sectionA.compareTo(sectionB) < 0 ? sectionA.isInclude(sectionB) : sectionB.isInclude(sectionA);
            if (isInclude) {
                return ResponseObj.fail(StatusCode.BIZ_FAILED, "此区间与" + sectionB.toString() + "有重叠");
            }
        }
        return ResponseObj.success();
    }
}
