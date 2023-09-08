package com.ruoyi.project.common.advise.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 房间信息对象 bar_serviceinfo
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalAdvise
{

   private Long id;

   private Long userId;

   @Excel(name = "意见/建议")
   private String advise;


   private String img;

   @Excel(name = "号码")
   private String phoneEmail;
}
