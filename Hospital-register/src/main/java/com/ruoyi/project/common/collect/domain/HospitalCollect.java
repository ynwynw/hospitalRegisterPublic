package com.ruoyi.project.common.collect.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 hospital_collect
 *
 * @author yanqinglan
 * @date 2023-04-28
 */
public class HospitalCollect extends BaseEntity
{
   private static final long serialVersionUID = 1L;

   /** $column.columnComment */
   @Excel(name = "用户id", readConverterExp = "$column.readConverterExp()")
   private Long userId;

   /** $column.columnComment */
   @Excel(name = "", readConverterExp = "$column.readConverterExp()")
   private String collect;

   /** $column.columnComment */
   @Excel(name = "医生名字", readConverterExp = "$column.readConverterExp()")
   private Long doctorId;

   /** $column.columnComment */
   private Long id;

   private String HospitalName;

   private String areaName;

   private String DoctorName;

   private String UserName;
   private Long hospitalId;

   public void setHospitalId(Long hospitalId) {
      this.hospitalId = hospitalId;
   }

   public Long getHospitalId() {
      return hospitalId;
   }

   public HospitalCollect() {
   }

   public HospitalCollect(Long userId, String collect, Long doctorId, Long id, String hospitalName, String areaName, String doctorName, String userName) {
      this.userId = userId;
      this.collect = collect;
      this.doctorId = doctorId;
      this.id = id;
      HospitalName = hospitalName;
      this.areaName = areaName;
      DoctorName = doctorName;
      UserName = userName;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public void setCollect(String collect) {
      this.collect = collect;
   }

   public void setDoctorId(Long doctorId) {
      this.doctorId = doctorId;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setHospitalName(String hospitalName) {
      HospitalName = hospitalName;
   }

   public void setAreaName(String areaName) {
      this.areaName = areaName;
   }

   public void setDoctorName(String doctorName) {
      DoctorName = doctorName;
   }

   public void setUserName(String userName) {
      UserName = userName;
   }

   public static long getSerialVersionUID() {
      return serialVersionUID;
   }

   public Long getUserId() {
      return userId;
   }

   public String getCollect() {
      return collect;
   }

   public Long getDoctorId() {
      return doctorId;
   }

   public Long getId() {
      return id;
   }

   public String getHospitalName() {
      return HospitalName;
   }

   public String getAreaName() {
      return areaName;
   }

   public String getDoctorName() {
      return DoctorName;
   }

   public String getUserName() {
      return UserName;
   }


   @Override
   public String toString() {
      return "HospitalCollect{" +
              "userId=" + userId +
              ", collect='" + collect + '\'' +
              ", doctorId=" + doctorId +
              ", id=" + id +
              ", HospitalName='" + HospitalName + '\'' +
              ", areaName='" + areaName + '\'' +
              ", DoctorName='" + DoctorName + '\'' +
              ", UserName='" + UserName + '\'' +
              '}';
   }
}
