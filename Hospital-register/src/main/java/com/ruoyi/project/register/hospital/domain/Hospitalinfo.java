package com.ruoyi.project.register.hospital.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公告信息对象 bar_noticeinfo
 *
 * @author surper
 * @date 2023/01/20
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hospitalinfo
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long hospitalId;


    @Excel(name = "主题")
    private String hospitalName;



}
