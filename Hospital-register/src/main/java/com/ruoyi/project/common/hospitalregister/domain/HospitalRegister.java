package com.ruoyi.project.common.hospitalregister.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 房间信息对象 bar_serviceinfo
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class HospitalRegister
{
   private Long registerId;
   private Long hospitalId;
   private Long doctorId;
   private Long areaId;

   public HospitalRegister() {
   }

   public HospitalRegister(Long registerId, Long hospitalId, Long doctorId, Long areaId) {
      this.registerId = registerId;
      this.hospitalId = hospitalId;
      this.doctorId = doctorId;
      this.areaId = areaId;
   }

   public Long getRegisterId() {
      return registerId;
   }

   public void setRegisterId(Long registerId) {
      this.registerId = registerId;
   }

   public Long getHospitalId() {
      return hospitalId;
   }

   public void setHospitalId(Long hospitalId) {
      this.hospitalId = hospitalId;
   }

   public Long getDoctorId() {
      return doctorId;
   }

   public void setDoctorId(Long doctorId) {
      this.doctorId = doctorId;
   }

   public Long getAreaId() {
      return areaId;
   }

   public void setAreaId(Long areaId) {
      this.areaId = areaId;
   }

   @Override
   public String toString() {
      return "HospitalRegister{" +
              "registerId=" + registerId +
              ", hospitalId=" + hospitalId +
              ", doctorId=" + doctorId +
              ", areaId=" + areaId +
              '}';
   }
}
