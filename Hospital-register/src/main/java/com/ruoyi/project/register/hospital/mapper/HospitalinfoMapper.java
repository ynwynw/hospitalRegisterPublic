package com.ruoyi.project.register.hospital.mapper;


import com.ruoyi.project.register.hospital.domain.Hospitalinfo;

import java.util.List;

/**
 * 公告信息Mapper接口
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
public interface HospitalinfoMapper
{
    Hospitalinfo selectById(Hospitalinfo hospitalinfo);

    Hospitalinfo selectByName(Hospitalinfo hospitalinfo);

    List<Hospitalinfo> selectAll(Hospitalinfo hospitalinfo);




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

    public List<Hospitalinfo> selectHospitalHospital(Hospitalinfo hospitalHospital);

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
     * 删除【请填写功能名称】
     *
     * @param hospitalId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteHospitalHospitalById(Long hospitalId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param hospitalIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteHospitalHospitalByIds(String[] hospitalIds);



}
