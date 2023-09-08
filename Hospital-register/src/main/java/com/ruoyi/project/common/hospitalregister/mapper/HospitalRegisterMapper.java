package com.ruoyi.project.common.hospitalregister.mapper;

//import com.ruoyi.project.common.hospitalregister.domain.HospitalCollect;

import com.ruoyi.project.common.hospitalregister.domain.HospitalRegister;

import java.util.List;

/**
 * 服务信息Mapper接口
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
public interface HospitalRegisterMapper
{

    List<HospitalRegister> selectDoctorIdByHospitalRegister(HospitalRegister hospitalRegister);

    List<HospitalRegister> selectHospitalIdByAreaId(HospitalRegister hospitalRegister);

    int updateHospitalRegister(HospitalRegister hospitalRegister);

    /**
     * 删除服务信息
     *
     * @param doctorId 服务信息ID
     * @return 结果
     */
    public int deleteHospitalinfoById(Long doctorId);

    /**
     * 批量删除服务信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHospitalinfoByIds(String[] ids);

    int inserteHospitalinfo(HospitalRegister hospitalRegister);

}
