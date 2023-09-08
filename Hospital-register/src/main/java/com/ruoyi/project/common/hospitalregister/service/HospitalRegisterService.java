package com.ruoyi.project.common.hospitalregister.service;


import com.ruoyi.project.common.hospitalregister.domain.HospitalRegister;
import com.ruoyi.project.register.serviceinfo.domain.Serviceinfo;

import java.util.List;

/**
 * 服务信息Service接口
 *
 * @author yanqinglan
 * @date 2023/0/20
 */
public interface HospitalRegisterService
{

    List<HospitalRegister> selectHospitalRegister(HospitalRegister hospitalRegister);


}
