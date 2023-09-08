package com.ruoyi.project.register.registerinfo.service;

import java.util.List;
import com.ruoyi.project.register.registerinfo.domain.Registerinfo;

/**
 * 理发师信息Service接口
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
public interface IRegisterinfoService
{
    /**
     * 查询理发师信息
     *
     * @param id 理发师信息ID
     * @return 理发师信息
     */
    public Registerinfo selectRegisterinfoById(Long id);

    /**
     * 查询理发师信息列表
     *
     * @param registerinfo 理发师信息
     * @return 理发师信息集合
     */
    public List<Registerinfo> selectRegisterinfoList(Registerinfo registerinfo);

    /**
     * 新增理发师信息
     *
     * @param registerinfo 理发师信息
     * @return 结果
     */
    public int insertRegisterinfo(Registerinfo registerinfo);

    /**
     * 修改理发师信息
     *
     * @param registerinfo 理发师信息
     * @return 结果
     */
    public int updateRegisterinfo(Registerinfo registerinfo);

    /**
     * 批量删除理发师信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRegisterinfoByIds(String ids);

    /**
     * 删除理发师信息信息
     *
     * @param id 理发师信息ID
     * @return 结果
     */
    public int deleteRegisterinfoById(Long id);




}
