package com.ruoyi.project.register.serviceinfo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 房间信息对象 bar_serviceinfo
 *
 * @author yanqinglan
 * @date 2023/01/20
 */

public class Serviceinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 楼层id */
    private Long registerId;

    private Long userId;

    /** 图片 */
//    @Excel(name = "图片")
    private String logo;

    /** 名字 */
    @Excel(name = "医生姓名")
    private String name;

    @Excel(name = "所在医院")
    private String hospitalName;

    @Excel(name = "所在地区")
    private String areaName;

    private Long areaId;

    /** 房间类型 */
    @Excel(name = "职称")
    private String typeInfo;

    /** 房间简介 */
    @Excel(name = "医生简介")
    private String introduce;

    /** 房间价格 */
    @Excel(name = "预约费用")
    private String price;

    /** 删除标记 */
    private String delFlag;

    private String registerName;

    private String collect;

    private Long hospitalId;


    public Serviceinfo(Long id, Long registerId, Long userId, String logo, String name, String hospitalName, String areaName, Long areaId, String typeInfo, String introduce, String price, String delFlag, String registerName, String collect, Long hospitalId) {
        this.id = id;
        this.registerId = registerId;
        this.userId = userId;
        this.logo = logo;
        this.name = name;
        this.hospitalName = hospitalName;
        this.areaName = areaName;
        this.areaId = areaId;
        this.typeInfo = typeInfo;
        this.introduce = introduce;
        this.price = price;
        this.delFlag = delFlag;
        this.registerName = registerName;
        this.collect = collect;
        this.hospitalId = hospitalId;
    }

    public Serviceinfo() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(String typeInfo) {
        this.typeInfo = typeInfo;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }
}
