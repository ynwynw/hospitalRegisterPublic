package com.ruoyi.project.common.advise.controller;

import java.util.List;

import com.ruoyi.project.common.advise.domain.HospitalAdvise;
import com.ruoyi.project.common.advise.service.HospitalAdviseService;
import com.ruoyi.project.register.registerinfo.domain.Registerinfo;
import com.ruoyi.project.register.registerinfo.service.IRegisterinfoService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
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
import com.ruoyi.project.register.serviceinfo.domain.Serviceinfo;
import com.ruoyi.project.register.serviceinfo.service.IServiceinfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 服务信息Controller
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Controller
@RequestMapping("/register/advise")
public class AdviseinfoController extends BaseController
{
    private String prefix = "register/adviseinfo";

//    @Autowired
//    private IServiceinfoService serviceinfoService;
//    @Autowired
//    private IRegisterinfoService registerinfoService;
//    @Autowired
//    private IUserService userService;

    @Autowired
    private HospitalAdviseService hospitalAdviseService;
//
    @RequiresPermissions("register:advise:view")
    @GetMapping()
    public String serviceinfo()
    {
        return prefix + "/adviseinfo";
    }
//
    /**
     * 查询服务信息列表
     */
    @RequiresPermissions("register:advise:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HospitalAdvise hospitalAdvise)
    {
        startPage();
        List<HospitalAdvise> list = hospitalAdviseService.selectAdviseinfoList(hospitalAdvise);
        return getDataTable(list);
    }
//
    /**
     * 导出服务信息列表
     */
    @RequiresPermissions("register:advise:export")
    @Log(title = "服务信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HospitalAdvise hospitalAdvise)
    {
        List<HospitalAdvise> list = hospitalAdviseService.selectAdviseinfoList(hospitalAdvise);
        ExcelUtil<HospitalAdvise> util = new ExcelUtil<HospitalAdvise>(HospitalAdvise.class);
        return util.exportExcel(list, "advise");
    }

//
    /**
     * 删除服务信息
     */
    @RequiresPermissions("register:advise:remove")
    @Log(title = "服务信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hospitalAdviseService.deleteAdviseinfoByIds(ids));
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
    @RequiresPermissions("register:advise:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HospitalAdvise hospitalAdvise)
    {
        return toAjax(hospitalAdviseService.insertHospitalAdvise(hospitalAdvise));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HospitalAdvise hospitalAdvise = hospitalAdviseService.selectHospitalAdviseById(id);
        mmap.put("hospitalAdvise", hospitalAdvise);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("register:advise:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HospitalAdvise hospitalAdvise)
    {
        return toAjax(hospitalAdviseService.updateHospitalAdvise(hospitalAdvise));
    }

    /**
     * 删除【请填写功能名称】
     */
//    @RequiresPermissions("register:advise:remove")
//    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
//    @PostMapping( "/remove")
//    @ResponseBody
//    public AjaxResult remove(String ids)
//    {
//        return toAjax(hospitalAdviseService.deleteHospitalAdviseByIds(ids));
//    }
}
