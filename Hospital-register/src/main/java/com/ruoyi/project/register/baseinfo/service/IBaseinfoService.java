package com.ruoyi.project.register.baseinfo.service;

import java.util.List;

import com.ruoyi.project.register.baseinfo.domain.Baseinfo;

/**
 * 茶之韵信息Service接口
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
public interface IBaseinfoService
{
    /**
     * 查询茶之韵信息
     *
     * @param id 茶之韵信息ID
     * @return 茶之韵信息
     */
    public Baseinfo selectBaseinfoById(Long id);

    /**
     * 查询茶之韵信息列表
     *
     * @param baseinfo 茶之韵信息
     * @return 茶之韵信息集合
     */
    public List<Baseinfo> selectBaseinfoList(Baseinfo baseinfo);

    /**
     * 新增茶之韵信息
     *
     * @param baseinfo 茶之韵信息
     * @return 结果
     */
    public int insertBaseinfo(Baseinfo baseinfo);

    /**
     * 修改茶之韵信息
     *
     * @param baseinfo 茶之韵信息
     * @return 结果
     */
    public int updateBaseinfo(Baseinfo baseinfo);

    /**
     * 批量删除茶之韵信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseinfoByIds(String ids);

    /**
     * 删除茶之韵信息信息
     *
     * @param id 茶之韵信息ID
     * @return 结果
     */
    public int deleteBaseinfoById(Long id);
}
