package com.ruoyi.project.register.baseinfo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 茶之韵信息对象 bar_shopinfo
 *
 * @author yanqinglan
 * @date 2022/01/20
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Baseinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** logo */
//    @Excel(name = "logo")
    private String logo;

    /** 店铺名称 */
    @Excel(name = "医院名称")
    private String name;

    /** 店铺介绍 */
    @Excel(name = "医院介绍")
    private String introduce;

    /** 营业时间 */
    @Excel(name = "营业时间")
    private String bussinessTime;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 地址 */
    @Excel(name = "医院地址")
    private String address;

    /** 经度 */
    private String longitude;

    /** 纬度 */
    private String latitude;


    private String iotInfo;

    private Long hospitalId;

    private Long areaId;

    private String areaName;

    private String hospitalName;



}
