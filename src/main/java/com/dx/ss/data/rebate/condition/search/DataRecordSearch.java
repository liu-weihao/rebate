package com.dx.ss.data.rebate.condition.search;

import com.dx.ss.data.rebate.condition.PeriodCondition;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DataRecordSearch extends PeriodCondition {

    /**
     * 指定管理员，一个管理员可以同时关联多个微信账号
     */
    private String userId;

    /**
     * 指定微信账号id
     */
    private Integer accountId;

}
