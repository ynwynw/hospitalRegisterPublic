package com.ruoyi.project.common.about.controller;

import java.util.List;

import com.ruoyi.project.common.about.domain.HospitalAbout;
import com.ruoyi.project.common.about.service.IHospitalAboutService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author yanqinglan
 * @date 2023-04-28
 */
@Controller
@RequestMapping("/register/about")
public class HospitalAboutController extends BaseController
{
    private String prefix = "register/about";

    @Autowired
    private IHospitalAboutService hospitalAboutService;

    @RequiresPermissions("register:about:view")
    @GetMapping()
    public String about()
    {
        return prefix + "/about";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("register:about:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HospitalAbout hospitalAbout)
    {
        startPage();
        List<HospitalAbout> list = hospitalAboutService.selectHospitalAboutList(hospitalAbout);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("register:about:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HospitalAbout hospitalAbout)
    {
        List<HospitalAbout> list = hospitalAboutService.selectHospitalAboutList(hospitalAbout);
        ExcelUtil<HospitalAbout> util = new ExcelUtil<HospitalAbout>(HospitalAbout.class);
        return util.exportExcel(list, "about");
    }

    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("register:about:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HospitalAbout hospitalAbout)
    {
        return toAjax(hospitalAboutService.insertHospitalAbout(hospitalAbout));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HospitalAbout hospitalAbout = hospitalAboutService.selectHospitalAboutById(id);
        mmap.put("hospitalAbout", hospitalAbout);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("register:about:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HospitalAbout hospitalAbout)
    {
        return toAjax(hospitalAboutService.updateHospitalAbout(hospitalAbout));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("register:about:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hospitalAboutService.deleteHospitalAboutByIds(ids));
    }
}
