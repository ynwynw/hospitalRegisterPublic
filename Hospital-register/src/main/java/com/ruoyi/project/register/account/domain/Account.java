package com.ruoyi.project.register.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 账户金额对象 bar_account
 *
 * @author yanqinglan
 * @date 2023/3/29
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;


    @Excel(name = "用户姓名")
    private String userName;


    /** 余额 */
    @Excel(name = "余额")
    private BigDecimal remainingSum;


}
