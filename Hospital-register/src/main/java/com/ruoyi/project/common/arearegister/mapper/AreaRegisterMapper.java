package com.ruoyi.project.common.arearegister.mapper;


import com.ruoyi.project.common.arearegister.domain.AreaRegister;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务信息Mapper接口
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
public interface AreaRegisterMapper
{
//    List<AreaRegister> selectByUserId(AreaRegister hospitalCollect);
//    int insertCollect(AreaRegister hospitalCollect);
//    int updateByUserId(AreaRegister hospitalCollect);

    List<AreaRegister> selectByAreaId(@Param("areaId") Long areaId);
}
