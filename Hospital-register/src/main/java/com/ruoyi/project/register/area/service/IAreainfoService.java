package com.ruoyi.project.register.area.service;


import com.ruoyi.project.register.area.domain.Areainfo;

import java.util.List;

/**
 * 服务信息Service接口
 *
 * @author yanqinglan
 * @date 2023/0/20
 */
public interface IAreainfoService
{
    List<Areainfo> selectAll();


    /**
     * 查询【请填写功能名称】
     *
     * @param areaId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public Areainfo selectHospitalAreaById(Long areaId);

    Areainfo selectAllaLL(Areainfo areainfo);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param hospitalArea 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Areainfo> selectHospitalAreaList(Areainfo hospitalArea);

    /**
     * 新增【请填写功能名称】
     *
     * @param hospitalArea 【请填写功能名称】
     * @return 结果
     */
    public int insertHospitalArea(Areainfo hospitalArea);

    /**
     * 修改【请填写功能名称】
     *
     * @param hospitalArea 【请填写功能名称】
     * @return 结果
     */
    public int updateHospitalArea(Areainfo hospitalArea);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHospitalAreaByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param areaId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteHospitalAreaById(Long areaId);

}
