package com.ruoyi.project.common.advise.mapper;

import com.ruoyi.project.common.advise.domain.HospitalAdvise;

import java.util.List;

/**
 * 服务信息Mapper接口
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
public interface HospitalAdviseMapper
{
    List<HospitalAdvise> selectByUserId(HospitalAdvise hospitalAdvise);
    int insertAdvise(HospitalAdvise hospitalAdvise);
    int updateByUserId(HospitalAdvise hospitalAdvise);
    List<HospitalAdvise> selectAdviseinfoList();
    public int deleteAdviseinfoByIds(String[] ids);



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
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteHospitalAdviseById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHospitalAdviseByIds(String[] ids);
}
