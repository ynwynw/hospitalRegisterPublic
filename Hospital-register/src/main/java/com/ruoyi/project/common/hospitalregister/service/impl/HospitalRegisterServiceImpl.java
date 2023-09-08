package com.ruoyi.project.common.hospitalregister.service.impl;

import com.ruoyi.project.common.hospitalregister.domain.HospitalRegister;
import com.ruoyi.project.common.hospitalregister.mapper.HospitalRegisterMapper;
import com.ruoyi.project.common.hospitalregister.service.HospitalRegisterService;
import com.ruoyi.project.register.serviceinfo.domain.Serviceinfo;
import com.ruoyi.project.register.serviceinfo.mapper.ServiceinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务信息Service业务层处理
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Service
public class HospitalRegisterServiceImpl implements HospitalRegisterService
{


    @Autowired
    private HospitalRegisterMapper hospitalRegisterMapper;


    @Override
    public List<HospitalRegister> selectHospitalRegister(HospitalRegister hospitalRegister) {
        return hospitalRegisterMapper.selectHospitalIdByAreaId(hospitalRegister);
    }
}
