package com.ruoyi.project.register.hospital.service;


import com.ruoyi.project.common.hospitalregister.domain.HospitalRegister;
import com.ruoyi.project.register.baseinfo.domain.Baseinfo;
import com.ruoyi.project.register.hospital.domain.Hospitalinfo;

import java.util.List;

/**
 * 公告信息Service接口
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
public interface HospitalinfoService
{
    List<Baseinfo> selectAll(HospitalRegister hospitalRegister);

    Hospitalinfo selectByName(Hospitalinfo hospitalinfo);


    List<Hospitalinfo> selectAll2(Hospitalinfo hospitalRegister);


    /**
     * 查询【请填写功能名称】
     *
     * @param hospitalId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public Hospitalinfo selectHospitalHospitalById(Long hospitalId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param hospitalHospital 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Hospitalinfo> selectHospitalHospitalList(Hospitalinfo hospitalHospital);

    /**
     * 新增【请填写功能名称】
     *
     * @param hospitalHospital 【请填写功能名称】
     * @return 结果
     */
    public int insertHospitalHospital(Hospitalinfo hospitalHospital);

    /**
     * 修改【请填写功能名称】
     *
     * @param hospitalHospital 【请填写功能名称】
     * @return 结果
     */
    public int updateHospitalHospital(Hospitalinfo hospitalHospital);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHospitalHospitalByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param hospitalId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteHospitalHospitalById(Long hospitalId);

}
