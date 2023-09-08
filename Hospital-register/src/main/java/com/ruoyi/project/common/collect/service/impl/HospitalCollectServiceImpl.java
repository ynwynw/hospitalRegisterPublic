package com.ruoyi.project.common.collect.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.common.advise.domain.HospitalAdvise;
import com.ruoyi.project.common.collect.domain.HospitalCollect;
import com.ruoyi.project.common.collect.mapper.HospitalCollectMapper;
import com.ruoyi.project.common.collect.service.HospitalCollectService;
import com.ruoyi.project.common.hospitalregister.domain.HospitalRegister;
import com.ruoyi.project.common.hospitalregister.mapper.HospitalRegisterMapper;
import com.ruoyi.project.common.hospitalregister.service.HospitalRegisterService;
import com.ruoyi.project.register.area.domain.Areainfo;
import com.ruoyi.project.register.area.mapper.AreainfoMapper;
import com.ruoyi.project.register.hospital.domain.Hospitalinfo;
import com.ruoyi.project.register.hospital.service.HospitalinfoService;
import com.ruoyi.project.register.registerinfo.domain.Registerinfo;
import com.ruoyi.project.register.registerinfo.mapper.RegisterinfoMapper;
import com.ruoyi.project.register.serviceinfo.domain.Serviceinfo;
import com.ruoyi.project.register.serviceinfo.mapper.ServiceinfoMapper;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.mapper.UserMapper;
import com.ruoyi.project.system.user.service.IUserService;
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
public class HospitalCollectServiceImpl implements HospitalCollectService {

    @Autowired
    private HospitalCollectMapper hospitalCollectMapper;

    @Autowired
    private ServiceinfoMapper serviceinfoMapper;


    @Autowired
    private HospitalinfoService hospitalinfoService;


    @Autowired
    private HospitalRegisterMapper hospitalRegisterMapper;


    @Autowired
    private AreainfoMapper areainfoMapper;


    @Autowired
    private IUserService userService;

    @Override
    public int updateCollect(HospitalCollect hospitalCollect) {
        //查询有没有对应的收藏
        List<HospitalCollect> hospitalCollects = hospitalCollectMapper.selectCollect(hospitalCollect);

        //如果没有，则添加一条新的收藏
        if (hospitalCollects.size() == 0) {
//            hospitalCollect.setCollect("0");
            int i = hospitalCollectMapper.insertCollect(hospitalCollect);
            return i;
        } else {
            //如果有，则修改收藏
            int i = hospitalCollectMapper.updateByUserId(hospitalCollect);
            return i;
        }

    }

    @Override
    public List<Serviceinfo> getCollect(HospitalCollect hospitalCollect) {
        ArrayList<Serviceinfo> list = new ArrayList<>();
        //查询收藏表，查出状态为1并且对应user的行
        List<HospitalCollect> hospitalCollects = hospitalCollectMapper.selectByUserId(hospitalCollect);
        if (hospitalCollects.size() > 0) {
            for (HospitalCollect collect : hospitalCollects) {
                Serviceinfo serviceinfo = serviceinfoMapper.selectServiceinfoById(collect.getDoctorId());
                list.add(serviceinfo);
            }
        }
        //利用查询出来的doctorid查询医生表对应的医生
        return list;
    }

    @Override
    public List<HospitalCollect> selectCollectinfoList(HospitalCollect hospitalCollect) {
        List<HospitalCollect> list = hospitalCollectMapper.selectCollectinfoList(hospitalCollect);
        ArrayList<HospitalCollect> list1 = new ArrayList<>();
        Hospitalinfo hospitalinfo = new Hospitalinfo();
        Areainfo areainfo = new Areainfo();
        for (HospitalCollect collect : list) {
            if (collect.getCollect().equals("1")) {
                Serviceinfo serviceinfo = serviceinfoMapper.selectServiceinfoById(collect.getDoctorId());
                if (serviceinfo != null) {
                    collect.setDoctorName(serviceinfo.getName());
                    hospitalinfo.setHospitalId(serviceinfo.getHospitalId());
                    Hospitalinfo hospitalinfo1 = hospitalinfoService.selectByName(hospitalinfo);
                    User user = userService.selectUserById(collect.getUserId());
                    collect.setUserName(user.getUserName());
                    collect.setHospitalName(hospitalinfo1.getHospitalName());
                    HospitalRegister hospitalRegister = new HospitalRegister();
                    hospitalRegister.setDoctorId(collect.getDoctorId());
                    hospitalRegister.setHospitalId(serviceinfo.getHospitalId());
                    List<HospitalRegister> hospitalRegisters = hospitalRegisterMapper.selectHospitalIdByAreaId(hospitalRegister);
                    for (HospitalRegister register : hospitalRegisters) {
                        areainfo.setAreaId(register.getAreaId());
                        Areainfo areainfos = areainfoMapper.selectAllaLL(areainfo);
                        collect.setAreaName(areainfos.getAreaName());

                    }
                    list1.add(collect);

                }
            }

        }

        return list1;

    }

    @Override
    public int deleteCollectinfoByIds(String ids) {
        return hospitalCollectMapper.deleteCollectinfoByIds(Convert.toStrArray(ids));
    }


    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public HospitalCollect selectHospitalCollectById(Long id) {
        return hospitalCollectMapper.selectHospitalCollectById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param hospitalCollect 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<HospitalCollect> selectHospitalCollectList(HospitalCollect hospitalCollect) {
        return hospitalCollectMapper.selectHospitalCollectList(hospitalCollect);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param hospitalCollect 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertHospitalCollect(HospitalCollect hospitalCollect) {
        return hospitalCollectMapper.insertHospitalCollect(hospitalCollect);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param hospitalCollect 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateHospitalCollect(HospitalCollect hospitalCollect) {
        return hospitalCollectMapper.updateHospitalCollect(hospitalCollect);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHospitalCollectByIds(String ids) {
        return hospitalCollectMapper.deleteHospitalCollectByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteHospitalCollectById(Long id) {
        return hospitalCollectMapper.deleteHospitalCollectById(id);
    }
}
