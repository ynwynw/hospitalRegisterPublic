package com.ruoyi.project.register.serviceinfo.mapper;

import java.util.List;
import com.ruoyi.project.register.serviceinfo.domain.Serviceinfo;
import org.apache.ibatis.annotations.Param;

/**
 * 服务信息Mapper接口
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
public interface ServiceinfoMapper
{
    /**
     * 查询服务信息
     *
     * @param id 服务信息ID
     * @return 服务信息
     */
    public Serviceinfo selectServiceinfoById(Long id);

    /**
     * 查询服务信息列表
     *
     * @param serviceinfo 服务信息
     * @return 服务信息集合
     */
    public List<Serviceinfo> selectServiceinfoList(Serviceinfo serviceinfo);

    /**
     * 新增服务信息
     *
     * @param serviceinfo 服务信息
     * @return 结果
     */
    public int insertServiceinfo(Serviceinfo serviceinfo);

    /**
     * 修改服务信息
     *
     * @param serviceinfo 服务信息
     * @return 结果
     */
    public int updateServiceinfo(Serviceinfo serviceinfo);

    /**
     * 删除服务信息
     *
     * @param id 服务信息ID
     * @return 结果
     */
    public int deleteServiceinfoById(Long id);

    /**
     * 批量删除服务信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteServiceinfoByIds(String[] ids);

    public int updateCollect(@Param("id") Long id,@Param("collect") String collect);



}
