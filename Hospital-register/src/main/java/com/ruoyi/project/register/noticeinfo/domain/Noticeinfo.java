package com.ruoyi.project.register.noticeinfo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 公告信息对象 bar_noticeinfo
 *
 * @author surper
 * @date 2023/01/20
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Noticeinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 图片 */
//    @Excel(name = "图片")
    private String logo;

    /** 主题 */
    @Excel(name = "主题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;


}
