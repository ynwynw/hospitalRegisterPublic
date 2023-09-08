package com.ruoyi.project.common.collect.service;


import com.ruoyi.project.common.advise.domain.HospitalAdvise;
import com.ruoyi.project.common.collect.domain.HospitalCollect;
import com.ruoyi.project.register.serviceinfo.domain.Serviceinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务信息Service接口
 *
 * @author yanqinglan
 * @date 2023/0/20
 */
public interface HospitalCollectService
{
    int updateCollect(HospitalCollect hospitalCollect);

    List<Serviceinfo> getCollect(HospitalCollect hospitalCollect);

    public List<HospitalCollect> selectCollectinfoList(HospitalCollect hospitalCollect);


    public int deleteCollectinfoByIds(String ids);


    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public HospitalCollect selectHospitalCollectById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param hospitalCollect 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<HospitalCollect> selectHospitalCollectList(HospitalCollect hospitalCollect);

    /**
     * 新增【请填写功能名称】
     *
     * @param hospitalCollect 【请填写功能名称】
     * @return 结果
     */
    public int insertHospitalCollect(HospitalCollect hospitalCollect);

    /**
     * 修改【请填写功能名称】
     *
     * @param hospitalCollect 【请填写功能名称】
     * @return 结果
     */
    public int updateHospitalCollect(HospitalCollect hospitalCollect);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHospitalCollectByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteHospitalCollectById(Long id);


}
