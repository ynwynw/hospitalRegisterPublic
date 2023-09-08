package com.ruoyi.project.register.registerinfo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 楼层信息对象 bar_barberinfo
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Registerinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** logo */
//    @Excel(name = "logo")
    private String logo;

    /** 楼层 */
    @Excel(name = "科室")
    private String name;

    /** 介绍 */
    @Excel(name = "介绍")
    private String introduce;

    /** 定位 */
    @Excel(name = "位置")
    private String position;


}
