package com.ruoyi.project.register.serviceorder.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.register.serviceorder.mapper.ServiceorderMapper;
import com.ruoyi.project.register.serviceorder.domain.Serviceorder;
import com.ruoyi.project.register.serviceorder.service.IServiceorderService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 服务订单Service业务层处理
 *
 * @author yanqinglan
 * @date 2023/01/23
 */
@Service
public class ServiceorderServiceImpl implements IServiceorderService
{
    @Autowired
    private ServiceorderMapper serviceorderMapper;

    /**
     * 查询服务订单
     *
     * @param id 服务订单ID
     * @return 服务订单
     */
    @Override
    public Serviceorder selectServiceorderById(Long id)
    {
        return serviceorderMapper.selectServiceorderById(id);
    }

    /**
     * 查询服务订单列表
     *
     * @param serviceorder 服务订单
     * @return 服务订单
     */
    @Override
    public List<Serviceorder> selectServiceorderList(Serviceorder serviceorder)
    {
        return serviceorderMapper.selectServiceorderList(serviceorder);
    }

    /**
     * 新增服务订单
     *
     * @param serviceorder 服务订单
     * @return 结果
     */
    @Override
    public int insertServiceorder(Serviceorder serviceorder)
    {
        serviceorder.setCreateTime(DateUtils.getNowDate());
        serviceorder.setUpdateTime(DateUtils.getNowDate());
        return serviceorderMapper.insertServiceorder(serviceorder);
    }

    /**
     * 修改服务订单
     *
     * @param serviceorder 服务订单
     * @return 结果
     */
    @Override
    public int updateServiceorder(Serviceorder serviceorder)
    {
        serviceorder.setUpdateTime(DateUtils.getNowDate());
        return serviceorderMapper.updateServiceorder(serviceorder);
    }

    /**
     * 删除服务订单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteServiceorderByIds(String ids)
    {
        return serviceorderMapper.deleteServiceorderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除服务订单信息
     *
     * @param id 服务订单ID
     * @return 结果
     */
    @Override
    public int deleteServiceorderById(Long id)
    {
        return serviceorderMapper.deleteServiceorderById(id);
    }

    @Override
    public List<Serviceorder> selectServiceorderByDoctorId(Integer id) {

        return serviceorderMapper.selectServiceorderByDoctorId(id);
    }

    @Override
    public List<Serviceorder> selectServiceorderInfoAppraiseList(Serviceorder serviceorder) {
        return serviceorderMapper.selectServiceorderInfoAppraiseList(serviceorder);
    }

    @Override
    public List<Serviceorder> selectServiceorderInfoList(Serviceorder serviceorder) {
        return serviceorderMapper.selectServiceorderInfoList(serviceorder);
    }
}
