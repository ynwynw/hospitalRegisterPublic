package com.ruoyi.project.common.about.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 hospital_about
 *
 * @author yanqinglan
 * @date 2023-04-28
 */
public class HospitalAbout
{
   private static final long serialVersionUID = 1L;

   /** $column.columnComment */
   private Long id;

   /** $column.columnComment */
   @Excel(name = "关于我们", readConverterExp = "$column.readConverterExp()")
   private String about;

   public void setId(Long id)
   {
      this.id = id;
   }

   public Long getId()
   {
      return id;
   }
   public void setAbout(String about)
   {
      this.about = about;
   }

   public String getAbout()
   {
      return about;
   }

   @Override
   public String toString() {
      return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
              .append("id", getId())
              .append("about", getAbout())
              .toString();
   }
}
