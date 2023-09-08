package com.ruoyi.project.register.baseinfo.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.common.hospitalregister.domain.HospitalRegister;
import com.ruoyi.project.common.hospitalregister.mapper.HospitalRegisterMapper;
import com.ruoyi.project.register.baseinfo.domain.Baseinfo;
import com.ruoyi.project.register.baseinfo.mapper.BaseinfoMapper;
import com.ruoyi.project.register.baseinfo.service.IBaseinfoService;
import com.ruoyi.project.register.hospital.domain.Hospitalinfo;
import com.ruoyi.project.register.hospital.mapper.HospitalinfoMapper;
import com.ruoyi.project.register.serviceinfo.domain.Serviceinfo;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.text.Convert;

import static com.ruoyi.common.utils.security.ShiroUtils.getSysUser;

/**
 * 茶之韵信息Service业务层处理
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Service
public class IBaseinfoServiceImpl implements IBaseinfoService
{
    @Autowired
    private BaseinfoMapper baseinfoMapper;


    @Autowired
    private HospitalRegisterMapper hospitalRegisterMapper;
    @Autowired
    private HospitalinfoMapper hospitalinfoMapper;

    /**
     * 查询茶之韵信息
     *
     * @param id 茶之韵信息ID
     * @return 茶之韵信息
     */
    @Override
    public Baseinfo selectBaseinfoById(Long id)
    {
        return baseinfoMapper.selectBaseinfoById(id);
    }

    /**
     * 查询茶之韵信息列表
     *
     * @param baseinfo 茶之韵信息
     * @return 茶之韵信息
     */
    @Override
    public List<Baseinfo> selectBaseinfoList(Baseinfo baseinfo)
    {


        return baseinfoMapper.selectBaseinfoList(baseinfo);
    }

    /**
     * 新增茶之韵信息
     *
     * @param baseinfo 茶之韵信息
     * @return 结果
     */
    @Override
    public int insertBaseinfo(Baseinfo baseinfo)
    {
        baseinfo.setCreateTime(DateUtils.getNowDate());
        return baseinfoMapper.insertBaseinfo(baseinfo);
    }

    /**
     * 修改茶之韵信息
     *
     * @param baseinfo 茶之韵信息
     * @return 结果
     */
    @Override
    public int updateBaseinfo(Baseinfo baseinfo)
    {
        baseinfo.setUpdateTime(DateUtils.getNowDate());
        baseinfo.setName(baseinfo.getHospitalName());
        if (baseinfo.getHospitalId() != null){
            Hospitalinfo hospitalinfo = new Hospitalinfo();
            hospitalinfo.setHospitalId(baseinfo.getHospitalId());
            Hospitalinfo hospitalinfo1 = hospitalinfoMapper.selectByName(hospitalinfo);
            baseinfo.setName(hospitalinfo1.getHospitalName());
        }
        int i = baseinfoMapper.updateBaseinfo(baseinfo);
//        if (i>0) {
//            HospitalRegister hospitalRegister = new HospitalRegister();
//
//                hospitalRegister.setHospitalId(baseinfo.getHospitalId());
//                hospitalRegister.setDoctorId(baseinfo.getId());
//                int i1 = hospitalRegisterMapper.updateHospitalRegister(hospitalRegister);
//
//
//        }
        return i;
    }

    /**
     * 删除茶之韵信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBaseinfoByIds(String ids)
    {
        return baseinfoMapper.deleteBaseinfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除茶之韵信息信息
     *
     * @param id 茶之韵信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseinfoById(Long id)
    {
        return baseinfoMapper.deleteBaseinfoById(id);
    }
}
