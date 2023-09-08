package com.ruoyi.project.register.serviceorder.service;

import java.util.List;
import com.ruoyi.project.register.serviceorder.domain.Serviceorder;

/**
 * 服务订单Service接口
 *
 * @author yanqinglan
 * @date 2023/01/23
 */
public interface IServiceorderService
{
    /**
     * 查询服务订单
     *
     * @param id 服务订单ID
     * @return 服务订单
     */
    public Serviceorder selectServiceorderById(Long id);

    /**
     * 查询服务订单列表
     *
     * @param serviceorder 服务订单
     * @return 服务订单集合
     */
    public List<Serviceorder> selectServiceorderList(Serviceorder serviceorder);

    /**
     * 新增服务订单
     *
     * @param serviceorder 服务订单
     * @return 结果
     */
    public int insertServiceorder(Serviceorder serviceorder);

    /**
     * 修改服务订单
     *
     * @param serviceorder 服务订单
     * @return 结果
     */
    public int updateServiceorder(Serviceorder serviceorder);

    /**
     * 批量删除服务订单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteServiceorderByIds(String ids);

    /**
     * 删除服务订单信息
     *
     * @param id 服务订单ID
     * @return 结果
     */
    public int deleteServiceorderById(Long id);


    public List<Serviceorder> selectServiceorderByDoctorId(Integer id);

    public List<Serviceorder> selectServiceorderInfoAppraiseList(Serviceorder serviceorder);
    public List<Serviceorder> selectServiceorderInfoList(Serviceorder serviceorder);


}
