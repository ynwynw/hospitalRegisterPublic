package com.ruoyi.project.register.area.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 房间信息对象 bar_serviceinfo
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Areainfo
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long areaId;


    /** 名字 */
    @Excel(name = "xxx")
    private String areaName;




}
