package com.ruoyi.project.common.about.mapper;

import com.ruoyi.project.common.about.domain.HospitalAbout;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author yanqinglan
 * @date 2023-04-28
 */
public interface HospitalAboutMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public HospitalAbout selectHospitalAboutById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param hospitalAbout 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<HospitalAbout> selectHospitalAboutList(HospitalAbout hospitalAbout);

    /**
     * 新增【请填写功能名称】
     *
     * @param hospitalAbout 【请填写功能名称】
     * @return 结果
     */
    public int insertHospitalAbout(HospitalAbout hospitalAbout);

    /**
     * 修改【请填写功能名称】
     *
     * @param hospitalAbout 【请填写功能名称】
     * @return 结果
     */
    public int updateHospitalAbout(HospitalAbout hospitalAbout);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteHospitalAboutById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHospitalAboutByIds(String[] ids);
}
