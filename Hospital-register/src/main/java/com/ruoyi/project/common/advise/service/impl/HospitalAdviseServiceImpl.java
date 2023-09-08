package com.ruoyi.project.common.advise.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.common.advise.domain.HospitalAdvise;
import com.ruoyi.project.common.advise.mapper.HospitalAdviseMapper;
import com.ruoyi.project.common.advise.service.HospitalAdviseService;
import com.ruoyi.project.register.registerinfo.domain.Registerinfo;
import com.ruoyi.project.register.serviceinfo.domain.Serviceinfo;
import com.ruoyi.project.register.serviceinfo.mapper.ServiceinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务信息Service业务层处理
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Service
public class HospitalAdviseServiceImpl implements HospitalAdviseService
{

    @Autowired
    private HospitalAdviseMapper hospitalAdviseMapper;

    @Override
    public int updateAdvise(HospitalAdvise hospitalAdvise) {

        List<HospitalAdvise> list = hospitalAdviseMapper.selectByUserId(hospitalAdvise);

        if (list.size() == 0){
            int i = hospitalAdviseMapper.insertAdvise(hospitalAdvise);
            System.out.println("irrrrrrrr"+i);
            return i;
        }else {

            int i = hospitalAdviseMapper.updateByUserId(hospitalAdvise);
            return i;
        }

    }

    @Override
    public List<HospitalAdvise> getAdvise(HospitalAdvise hospitalAdvise) {
        //查询收藏表，查出状态为1并且对应user的行
        List<HospitalAdvise> list = hospitalAdviseMapper.selectByUserId(hospitalAdvise);

        return list;
    }

    @Override
    public List<HospitalAdvise> selectAdviseinfoList(HospitalAdvise hospitalAdvise) {
        List<HospitalAdvise> list = hospitalAdviseMapper.selectAdviseinfoList();

        return list;
    }

    @Override
    public int deleteAdviseinfoByIds(String ids) {
        return hospitalAdviseMapper.deleteAdviseinfoByIds(Convert.toStrArray(ids));
    }


//    @Autowired
//    private HospitalAdviseMapper hospitalAdviseMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public HospitalAdvise selectHospitalAdviseById(Long id)
    {
        return hospitalAdviseMapper.selectHospitalAdviseById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param hospitalAdvise 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<HospitalAdvise> selectHospitalAdviseList(HospitalAdvise hospitalAdvise)
    {
        return hospitalAdviseMapper.selectHospitalAdviseList(hospitalAdvise);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param hospitalAdvise 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertHospitalAdvise(HospitalAdvise hospitalAdvise)
    {
        return hospitalAdviseMapper.insertHospitalAdvise(hospitalAdvise);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param hospitalAdvise 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateHospitalAdvise(HospitalAdvise hospitalAdvise)
    {
        return hospitalAdviseMapper.updateHospitalAdvise(hospitalAdvise);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHospitalAdviseByIds(String ids)
    {
        return hospitalAdviseMapper.deleteHospitalAdviseByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteHospitalAdviseById(Long id)
    {
        return hospitalAdviseMapper.deleteHospitalAdviseById(id);
    }
}
