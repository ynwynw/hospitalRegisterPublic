package com.ruoyi.project.common.arearegister.service.impl;

import com.ruoyi.project.common.arearegister.domain.AreaRegister;
import com.ruoyi.project.common.arearegister.mapper.AreaRegisterMapper;
import com.ruoyi.project.common.arearegister.service.AreaRegisterService;
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
public class AreaRegisterServiceImpl implements AreaRegisterService
{

//    @Autowired
//    private AreaRegisterMapper hospitalCollectMapper;
//
//    @Autowired
//    private ServiceinfoMapper serviceinfoMapper;
//    @Override
//    public int updateCollect(AreaRegister hospitalCollect) {
//        //查询有没有对应的收藏
//        List<AreaRegister> hospitalCollects = hospitalCollectMapper.selectByUserId(hospitalCollect);
//
//        //如果没有，则添加一条新的收藏
//        if (hospitalCollects.size() == 0){
////            hospitalCollect.setCollect("0");
//            int i = hospitalCollectMapper.insertCollect(hospitalCollect);
//            return i;
//        }else {
//            //如果有，则修改收藏
//            int i = hospitalCollectMapper.updateByUserId(hospitalCollect);
//            return i;
//        }
//
//    }
//
//    @Override
//    public List<AreaRegister> getCollect(AreaRegister hospitalCollect) {
//        ArrayList<Serviceinfo> list = new ArrayList<>();
//        //查询收藏表，查出状态为1并且对应user的行
//        List<HospitalCollect> hospitalCollects = hospitalCollectMapper.selectByUserId(hospitalCollect);
//        if (hospitalCollects.size() > 0 ){
//            for (HospitalCollect collect : hospitalCollects) {
//                Serviceinfo serviceinfo = serviceinfoMapper.selectServiceinfoById(collect.getDoctorId());
//                list.add(serviceinfo);
//            }
//        }
//        //利用查询出来的doctorid查询医生表对应的医生
//        return list;
//    }
}
