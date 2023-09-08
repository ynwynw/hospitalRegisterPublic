package com.ruoyi.project.common.about.service.impl;

import java.util.List;

import com.ruoyi.project.common.about.domain.HospitalAbout;
import com.ruoyi.project.common.about.mapper.HospitalAboutMapper;
import com.ruoyi.project.common.about.service.IHospitalAboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author yanqinglan
 * @date 2023-04-28
 */
@Service
public class HospitalAboutServiceImpl implements IHospitalAboutService
{
    @Autowired
    private HospitalAboutMapper hospitalAboutMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public HospitalAbout selectHospitalAboutById(Long id)
    {
        return hospitalAboutMapper.selectHospitalAboutById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param hospitalAbout 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<HospitalAbout> selectHospitalAboutList(HospitalAbout hospitalAbout)
    {
        return hospitalAboutMapper.selectHospitalAboutList(hospitalAbout);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param hospitalAbout 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertHospitalAbout(HospitalAbout hospitalAbout)
    {
        return hospitalAboutMapper.insertHospitalAbout(hospitalAbout);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param hospitalAbout 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateHospitalAbout(HospitalAbout hospitalAbout)
    {
        return hospitalAboutMapper.updateHospitalAbout(hospitalAbout);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHospitalAboutByIds(String ids)
    {
        return hospitalAboutMapper.deleteHospitalAboutByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteHospitalAboutById(Long id)
    {
        return hospitalAboutMapper.deleteHospitalAboutById(id);
    }
}
