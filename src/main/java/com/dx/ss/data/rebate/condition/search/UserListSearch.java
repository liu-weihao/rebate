package com.dx.ss.data.rebate.condition.search;

import com.dx.ss.data.rebate.condition.PageableCondition;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserListSearch extends PageableCondition {

    private String phone;

    private String name;
}
