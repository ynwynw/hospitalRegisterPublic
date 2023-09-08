package com.ruoyi.project.common.advise.service;


import com.ruoyi.project.common.advise.domain.HospitalAdvise;
import com.ruoyi.project.register.serviceinfo.domain.Serviceinfo;

import java.util.List;

/**
 * 服务信息Service接口
 *
 * @author yanqinglan
 * @date 2023/0/20
 */
public interface HospitalAdviseService
{
    int updateAdvise(HospitalAdvise hospitalAdvise);

    List<HospitalAdvise> getAdvise(HospitalAdvise hospitalAdvise);

    public List<HospitalAdvise> selectAdviseinfoList(HospitalAdvise hospitalAdvise);

    public int deleteAdviseinfoByIds(String ids);



    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public HospitalAdvise selectHospitalAdviseById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param hospitalAdvise 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<HospitalAdvise> selectHospitalAdviseList(HospitalAdvise hospitalAdvise);

    /**
     * 新增【请填写功能名称】
     *
     * @param hospitalAdvise 【请填写功能名称】
     * @return 结果
     */
    public int insertHospitalAdvise(HospitalAdvise hospitalAdvise);

    /**
     * 修改【请填写功能名称】
     *
     * @param hospitalAdvise 【请填写功能名称】
     * @return 结果
     */
    public int updateHospitalAdvise(HospitalAdvise hospitalAdvise);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHospitalAdviseByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteHospitalAdviseById(Long id);
}
