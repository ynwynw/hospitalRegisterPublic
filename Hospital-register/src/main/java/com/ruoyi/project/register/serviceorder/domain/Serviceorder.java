package com.ruoyi.project.register.serviceorder.domain;

import com.ruoyi.project.register.serviceinfo.domain.Serviceinfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 服务订单对象 bar_serviceorder
 *
 * @author yanqinglan
 * @date 2023/01/23
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Serviceorder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 理发师ID */
    private Long registerId;

    /** 服务ID */
    private Long serviceId;

    private Long userId;


    @Excel(name = "用户姓名")
    private String userName;

    @Excel(name = "患者姓名")
    private String contactName;

    @Excel(name = "联系电话")
    private String contactPhone;


    /** 理发师姓名 */
    @Excel(name = "科室")
    private String registerName;

    /** 服务名称 */
    @Excel(name = "医生")
    private String serverName;

    /** 服务类型 */
    @Excel(name = "职称")
    private String typeInfo;

    /** 价格 */
    @Excel(name = "挂号费")
    private String price;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private String payStatus;

    /** 预约日期 */
    @Excel(name = "预约日期")
    private String orderDate;

    /** 预约时间 */
    @Excel(name = "预约时间")
    private String orderTime;


    /** 疾病描述 */
    @Excel(name = "疾病描述")
    private String illTroditional;
    /** 疾病描述 */
    @Excel(name = "取消原因")
    private String reasonCancel ;

    private Long hospitalId;

    private String hospitalName;

    private String areaName;

    private Long areaId;


    /** 服务状态 */
    @Excel(name = "就诊状态")
    private String serviceStatus;

    /** 评价分数 */
    @Excel(name = "评价分数")
    private String evaluatePoint;

    private String stateTip;

    private String stateTipColor = "#fa436a";



    private Serviceinfo serviceinfo;






}
