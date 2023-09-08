package com.ruoyi.project.register.area.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.register.area.domain.Areainfo;
import com.ruoyi.project.register.area.mapper.AreainfoMapper;
import com.ruoyi.project.register.area.service.IAreainfoService;
import com.ruoyi.project.register.registerinfo.domain.Registerinfo;
import com.ruoyi.project.register.registerinfo.mapper.RegisterinfoMapper;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ruoyi.common.utils.security.ShiroUtils.getSysUser;

/**
 * 服务信息Service业务层处理
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Service
public class AreainfoServiceImpl implements IAreainfoService
{

    @Autowired
    private AreainfoMapper areainfoMapper;


    @Override
    public List<Areainfo> selectAll() {

        return areainfoMapper.selectAll();
    }



    /**
     * 查询【请填写功能名称】
     *
     * @param areaId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public Areainfo selectHospitalAreaById(Long areaId)
    {
        return areainfoMapper.selectHospitalAreaById(areaId);
    }

    @Override
    public Areainfo selectAllaLL(Areainfo areainfo) {
        return areainfoMapper.selectAllaLL(areainfo);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param hospitalArea 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Areainfo> selectHospitalAreaList(Areainfo hospitalArea)
    {

        return areainfoMapper.selectHospitalAreaList(hospitalArea);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param hospitalArea 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertHospitalArea(Areainfo hospitalArea)
    {
        List<Areainfo> areainfos = areainfoMapper.selectHospitalArea(hospitalArea);
        if (areainfos.size()>0){
            return 2;
        }else {

            int i = areainfoMapper.insertHospitalArea(hospitalArea);
            return i;
        }
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param hospitalArea 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateHospitalArea(Areainfo hospitalArea)
    {
        return areainfoMapper.updateHospitalArea(hospitalArea);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHospitalAreaByIds(String ids)
    {
        return areainfoMapper.deleteHospitalAreaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param areaId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteHospitalAreaById(Long areaId)
    {
        return areainfoMapper.deleteHospitalAreaById(areaId);
    }

}
