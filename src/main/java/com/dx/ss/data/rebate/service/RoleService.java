package com.dx.ss.data.rebate.service;

import com.dx.ss.data.rebate.dal.beans.RoleInfo;
import com.dx.ss.data.rebate.dal.mapper.RoleInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleInfoMapper roleMapper;

    public List<RoleInfo> getRoles() {
        return roleMapper.selectAll();
    }
}
