package com.ruoyi.project.register.registerinfo.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.register.serviceinfo.domain.Serviceinfo;
import com.ruoyi.project.register.serviceinfo.mapper.ServiceinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.register.registerinfo.mapper.RegisterinfoMapper;
import com.ruoyi.project.register.registerinfo.domain.Registerinfo;
import com.ruoyi.project.register.registerinfo.service.IRegisterinfoService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 理发师信息Service业务层处理
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Service
public class RegisterinfoServiceImpl implements IRegisterinfoService {
    @Autowired
    private RegisterinfoMapper registerinfoMapper;

    @Autowired
    private ServiceinfoMapper serviceinfoMapper;

    /**
     * 查询理发师信息
     *
     * @param id 理发师信息ID
     * @return 理发师信息
     */
    @Override
    public Registerinfo selectRegisterinfoById(Long id) {
        return registerinfoMapper.selectRegisterinfoById(id);
    }

    /**
     * 查询理发师信息列表
     *
     * @param registerinfo 理发师信息
     * @return 理发师信息
     */
    @Override
    public List<Registerinfo> selectRegisterinfoList(Registerinfo registerinfo) {

        return registerinfoMapper.selectRegisterinfoList(registerinfo);
    }

    /**
     * 新增理发师信息
     *
     * @param registerinfo 理发师信息
     * @return 结果
     */
    @Override
    public int insertRegisterinfo(Registerinfo registerinfo) {
        List<Registerinfo> registerinfoList = registerinfoMapper.selectRegisterinfo(registerinfo);

        if (registerinfoList.size() > 0) {
            return 2;
        } else {
            registerinfo.setCreateTime(DateUtils.getNowDate());
            return registerinfoMapper.insertRegisterinfo(registerinfo);
        }
    }

    /**
     * 修改理发师信息
     *
     * @param registerinfo 理发师信息
     * @return 结果
     */
    @Override
    public int updateRegisterinfo(Registerinfo registerinfo) {
        registerinfo.setUpdateTime(DateUtils.getNowDate());
        return registerinfoMapper.updateRegisterinfo(registerinfo);
    }

    /**
     * 删除理发师信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRegisterinfoByIds(String ids) {
        return registerinfoMapper.deleteRegisterinfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除理发师信息信息
     *
     * @param id 理发师信息ID
     * @return 结果
     */
    @Override
    public int deleteRegisterinfoById(Long id) {
        return registerinfoMapper.deleteRegisterinfoById(id);
    }


}
