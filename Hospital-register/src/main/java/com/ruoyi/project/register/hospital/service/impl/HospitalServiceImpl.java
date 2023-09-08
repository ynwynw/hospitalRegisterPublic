package com.ruoyi.project.register.hospital.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.common.hospitalregister.domain.HospitalRegister;
import com.ruoyi.project.common.hospitalregister.mapper.HospitalRegisterMapper;
import com.ruoyi.project.register.baseinfo.domain.Baseinfo;
import com.ruoyi.project.register.baseinfo.mapper.BaseinfoMapper;
import com.ruoyi.project.register.hospital.domain.Hospitalinfo;
import com.ruoyi.project.register.hospital.mapper.HospitalinfoMapper;
import com.ruoyi.project.register.hospital.service.HospitalinfoService;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.register.hospital.mapper.HospitalinfoMapper;

import java.util.ArrayList;
import java.util.List;

import static com.ruoyi.common.utils.security.ShiroUtils.getSysUser;

/**
 * 公告信息Service业务层处理
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Service
public class HospitalServiceImpl implements HospitalinfoService
{

    @Autowired
    private HospitalinfoMapper hospitalinfoMapper;


    @Autowired
    private HospitalRegisterMapper hospitalRegisterMapper;


    @Autowired
    private BaseinfoMapper baseinfoMapper;

    @Override
    public List<Baseinfo> selectAll(HospitalRegister hospitalRegister) {

        ArrayList<Baseinfo> list1 = new ArrayList<>();
//        Hospitalinfo hospitalinfo = new Hospitalinfo();
        Baseinfo baseinfo = new Baseinfo();
        List<HospitalRegister> list = hospitalRegisterMapper.selectHospitalIdByAreaId(hospitalRegister);
        if (list == null){
            return null;
        }
        for (HospitalRegister register : list) {
//            hospitalinfo.setHospitalId(register.getHospitalId());
            baseinfo.setHospitalId(register.getHospitalId());
            List<Baseinfo> baseinfos = baseinfoMapper.selectBaseinfoList(baseinfo);
//            Hospitalinfo hospitalinfos = hospitalinfoMapper.selectById(hospitalinfo);
            for (Baseinfo baseinfo1 : baseinfos) {

                list1.add(baseinfo1);
            }
        }
        return list1;
    }

    @Override
    public Hospitalinfo selectByName(Hospitalinfo hospitalinfo) {
        Hospitalinfo hospitalinfos = hospitalinfoMapper.selectByName(hospitalinfo);
        return hospitalinfos;
    }

    @Override
    public List<Hospitalinfo> selectAll2(Hospitalinfo hospitalRegister) {
        return hospitalinfoMapper.selectAll(hospitalRegister);
    }


    /**
     * 查询【请填写功能名称】
     *
     * @param hospitalId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public Hospitalinfo selectHospitalHospitalById(Long hospitalId)
    {
        return hospitalinfoMapper.selectHospitalHospitalById(hospitalId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param hospitalHospital 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Hospitalinfo> selectHospitalHospitalList(Hospitalinfo hospitalHospital)
    {
        return hospitalinfoMapper.selectHospitalHospitalList(hospitalHospital);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param hospitalHospital 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertHospitalHospital(Hospitalinfo hospitalHospital)
    {

        List<Hospitalinfo> hospitalinfos = hospitalinfoMapper.selectHospitalHospital(hospitalHospital);
        if (hospitalinfos.size()>0){
            return 2;
        }else {

            return hospitalinfoMapper.insertHospitalHospital(hospitalHospital);
        }
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param hospitalHospital 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateHospitalHospital(Hospitalinfo hospitalHospital)
    {
        return hospitalinfoMapper.updateHospitalHospital(hospitalHospital);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHospitalHospitalByIds(String ids)
    {
        return hospitalinfoMapper.deleteHospitalHospitalByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param hospitalId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteHospitalHospitalById(Long hospitalId)
    {
        return hospitalinfoMapper.deleteHospitalHospitalById(hospitalId);
    }
}
