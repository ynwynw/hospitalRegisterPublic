package com.ruoyi.project.register.address.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 地址管理对象 bar_address
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户id */
    private Long userId;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 区域地址 */
    @Excel(name = "区域地址")
    private String addressCode;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String addressDetail;

    /** 删除标记 */
    private String delFlag;

    private String cardNo;

    private String sex;


}
