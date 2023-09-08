package com.ruoyi.project.register.serviceinfo.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.common.collect.domain.HospitalCollect;
import com.ruoyi.project.common.collect.mapper.HospitalCollectMapper;
import com.ruoyi.project.common.hospitalregister.domain.HospitalRegister;
import com.ruoyi.project.common.hospitalregister.mapper.HospitalRegisterMapper;
import com.ruoyi.project.register.area.domain.Areainfo;
import com.ruoyi.project.register.area.mapper.AreainfoMapper;
import com.ruoyi.project.register.hospital.domain.Hospitalinfo;
import com.ruoyi.project.register.hospital.mapper.HospitalinfoMapper;
import com.ruoyi.project.register.registerinfo.domain.Registerinfo;
import com.ruoyi.project.register.registerinfo.mapper.RegisterinfoMapper;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.register.serviceinfo.mapper.ServiceinfoMapper;
import com.ruoyi.project.register.serviceinfo.domain.Serviceinfo;
import com.ruoyi.project.register.serviceinfo.service.IServiceinfoService;
import com.ruoyi.common.utils.text.Convert;

import static com.ruoyi.common.utils.security.ShiroUtils.getSysUser;

/**
 * 服务信息Service业务层处理
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Service
public class ServiceinfoServiceImpl implements IServiceinfoService {
    @Autowired
    private ServiceinfoMapper serviceinfoMapper;

    @Autowired
    private RegisterinfoMapper registerinfoMapper;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private HospitalinfoMapper hospitalinfoMapper;


    @Autowired
    private AreainfoMapper areainfoMapper;


    @Autowired
    private HospitalRegisterMapper hospitalRegisterMapper;


    @Autowired
    private HospitalCollectMapper hospitalCollectMapper;


    /**
     * 查询服务信息
     *
     * @param id 服务信息ID
     * @return 服务信息
     */
    @Override
    public Serviceinfo selectServiceinfoById(Long id) {
        return serviceinfoMapper.selectServiceinfoById(id);
    }

    /**
     * 查询服务信息列表
     *
     * @param serviceinfo 服务信息
     * @return 服务信息
     */
    @Override
    public List<Serviceinfo> selectServiceinfoList(Serviceinfo serviceinfo) {

        List<Serviceinfo> list = serviceinfoMapper.selectServiceinfoList(serviceinfo);
        if (CollUtil.isNotEmpty(list)) {
            list.forEach(data -> {
                if (ObjectUtil.isNotEmpty(data.getRegisterId())) {
                    Registerinfo registerinfo = registerinfoMapper.selectRegisterinfoById(data.getRegisterId());
                    if (ObjectUtil.isNotEmpty(registerinfo)) {
                        data.setRegisterName(registerinfo.getName());
                    }
                }
                if (ObjectUtil.isNotEmpty(data.getHospitalId())) {
                    Hospitalinfo hospitalinfo = new Hospitalinfo();
                    hospitalinfo.setHospitalId(data.getHospitalId());
                    Hospitalinfo hospitalinfo1 = hospitalinfoMapper.selectById(hospitalinfo);
                    if (ObjectUtil.isNotEmpty(hospitalinfo1)) {
                        data.setHospitalName(hospitalinfo1.getHospitalName());
                    }

                    if (ObjectUtil.isNotEmpty(data.getId())) {
                        HospitalRegister hospitalRegister = new HospitalRegister();
                        hospitalRegister.setDoctorId(data.getId());
                        hospitalRegister.setHospitalId(data.getHospitalId());
                        List<HospitalRegister> hospitalRegisters = hospitalRegisterMapper.selectHospitalIdByAreaId(hospitalRegister);
                        if (hospitalRegisters.size() > 0) {
                            for (HospitalRegister register : hospitalRegisters) {
                                Areainfo areainfo1 = new Areainfo();
                                areainfo1.setAreaId(register.getAreaId());
                                Areainfo areainfo = areainfoMapper.selectAllaLL(areainfo1);
                                if (ObjectUtil.isNotEmpty(areainfo)) {
                                    data.setAreaName(areainfo.getAreaName());
                                }
                            }
                        }
                    }

                }

            });
        }
        return list;
    }

    /**
     * 新增服务信息
     *
     * @param serviceinfo 服务信息
     * @return 结果
     */
    @Override
    public int insertServiceinfo(Serviceinfo serviceinfo) {
        serviceinfo.setCreateTime(DateUtils.getNowDate());
        serviceinfo.setCollect("0");
        if (ObjectUtil.isNotEmpty(serviceinfo.getUserId())) {
            User user = userMapper.selectUserById(serviceinfo.getUserId());
            if (ObjectUtil.isNotEmpty(user)) {
                serviceinfo.setName(user.getUserName());
            }
        }
        int i = serviceinfoMapper.insertServiceinfo(serviceinfo);
        Serviceinfo serviceinfo1 = serviceinfoMapper.selectServiceinfoById(serviceinfo.getId());
        Areainfo areainfo = new Areainfo();
        areainfo.setAreaName(serviceinfo.getAreaName());
        Areainfo areainfo1 = areainfoMapper.selectAllaLL(areainfo);
        HospitalRegister hospitalRegister = new HospitalRegister();
        hospitalRegister.setAreaId(areainfo1.getAreaId());
        hospitalRegister.setHospitalId(serviceinfo1.getHospitalId());
        hospitalRegister.setRegisterId(serviceinfo1.getRegisterId());
        hospitalRegister.setDoctorId(serviceinfo1.getId());
        int i1 = hospitalRegisterMapper.inserteHospitalinfo(hospitalRegister);
        return i;
    }

    /**
     * 修改服务信息
     *
     * @param serviceinfo 服务信息
     * @return 结果
     */
    @Override
    public int updateServiceinfo(Serviceinfo serviceinfo) {
        serviceinfo.setUpdateTime(DateUtils.getNowDate());
        if (ObjectUtil.isNotEmpty(serviceinfo.getUserId())) {
            User user = userMapper.selectUserById(serviceinfo.getUserId());
            if (ObjectUtil.isNotEmpty(user)) {
                serviceinfo.setName(user.getUserName());
            }
        }
        int i = serviceinfoMapper.updateServiceinfo(serviceinfo);
        if (i > 0) {
            List<Serviceinfo> serviceinfos = serviceinfoMapper.selectServiceinfoList(serviceinfo);
            if (serviceinfos.size() > 0) {


                HospitalRegister hospitalRegister = new HospitalRegister();
                for (Serviceinfo serviceinfo1 : serviceinfos) {
                    hospitalRegister.setHospitalId(serviceinfo1.getHospitalId());
                    hospitalRegister.setDoctorId(serviceinfo1.getId());
                    hospitalRegister.setRegisterId(serviceinfo1.getRegisterId());
                    hospitalRegister.setAreaId(serviceinfo1.getAreaId());
                    int i1 = hospitalRegisterMapper.updateHospitalRegister(hospitalRegister);

                }

            }
        }
        return i;
    }

    /**
     * 删除服务信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteServiceinfoByIds(String ids) {
        HospitalCollect hospitalCollect = new HospitalCollect();
        int i = serviceinfoMapper.deleteServiceinfoByIds(Convert.toStrArray(ids));
        if (i > 0) {
            int i1 = hospitalRegisterMapper.deleteHospitalinfoByIds(Convert.toStrArray(ids));
            String[] split = ids.split(",");
            for (String s : split) {
                hospitalCollect.setDoctorId(Long.parseLong(s));
                if (hospitalCollect.getDoctorId() != null) {
                    List<HospitalCollect> hospitalCollects = hospitalCollectMapper.selectHospitalCollectByDoctorId(hospitalCollect);
                    if (hospitalCollects.size() > 0) {
                        int i2 = hospitalCollectMapper.deleteHospitalCollectByhospitalId(Long.parseLong(s));

                    }
                }


            }

        }
        return i;
    }

    /**
     * 删除服务信息信息
     *
     * @param id 服务信息ID
     * @return 结果
     */
    @Override
    public int deleteServiceinfoById(Long id) {
        HospitalCollect hospitalCollect = new HospitalCollect();
        int i = serviceinfoMapper.deleteServiceinfoById(id);
        if (i > 0) {
            int i1 = hospitalRegisterMapper.deleteHospitalinfoById(id);
            hospitalCollect.setDoctorId(id);
            List<HospitalCollect> hospitalCollects = hospitalCollectMapper.selectHospitalCollectList(hospitalCollect);
            if (hospitalCollects.size() > 0) {
                int i2 = hospitalCollectMapper.deleteHospitalCollectByhospitalId(id);

            }
        }
        return i;
    }

    @Override
    public int updateCollect(Long id, String collect) {

        return serviceinfoMapper.updateCollect(id, collect);
    }

    @Override
    public List<Serviceinfo> selectDoctorListByid(HospitalRegister h) {

        ArrayList<Serviceinfo> list1 = new ArrayList<>();
        List<HospitalRegister> list = hospitalRegisterMapper.selectDoctorIdByHospitalRegister(h);
        if (list == null) {
            return null;
        }
        for (HospitalRegister hospitalRegister : list) {
            Serviceinfo serviceinfo = serviceinfoMapper.selectServiceinfoById(hospitalRegister.getDoctorId());
            list1.add(serviceinfo);
        }
        return list1;
    }
}
