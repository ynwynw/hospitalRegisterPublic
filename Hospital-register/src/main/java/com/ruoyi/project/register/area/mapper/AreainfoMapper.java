package com.ruoyi.project.register.area.mapper;

import com.ruoyi.project.register.area.domain.Areainfo;
import com.ruoyi.project.register.registerinfo.domain.Registerinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务信息Mapper接口
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
public interface AreainfoMapper {


    List<Areainfo> selectAll();

    List<Registerinfo> selectRegisterById(Long id);


    Areainfo selectAllaLL(Areainfo areainfo);


    /**
     * 查询【请填写功能名称】
     *
     * @param areaId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public Areainfo selectHospitalAreaById(Long areaId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param hospitalArea 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Areainfo> selectHospitalAreaList(Areainfo hospitalArea);
    public List<Areainfo> selectHospitalArea(Areainfo hospitalArea);

    /**
     * 新增【请填写功能名称】
     *
     * @param hospitalArea 【请填写功能名称】
     * @return 结果
     */
    public int insertHospitalArea(Areainfo hospitalArea);

    /**
     * 修改【请填写功能名称】
     *
     * @param hospitalArea 【请填写功能名称】
     * @return 结果
     */
    public int updateHospitalArea(Areainfo hospitalArea);

    /**
     * 删除【请填写功能名称】
     *
     * @param areaId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteHospitalAreaById(Long areaId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param areaIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteHospitalAreaByIds(String[] areaIds);
}
