package com.ruoyi.project.common.collect.controller;

import java.util.List;

import com.ruoyi.project.common.collect.domain.HospitalCollect;
import com.ruoyi.project.common.collect.service.HospitalCollectService;
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
@RequestMapping("/register/collect")
public class HospitalCollectController extends BaseController
{
    private String prefix = "/register/collectinfo";

//    @Autowired
//    private IServiceinfoService serviceinfoService;
//    @Autowired
//    private IRegisterinfoService registerinfoService;
//    @Autowired
//    private IUserService userService;


    @Autowired
    private HospitalCollectService hospitalCollectService;

    @RequiresPermissions("register:collect:view")
    @GetMapping()
    public String collectinfo()
    {
        return prefix + "/collectinfo";
    }

    /**
     * 查询服务信息列表
     */
    @RequiresPermissions("register:collect:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HospitalCollect hospitalCollect)
    {
        startPage();
        User user = getSysUser();
        if (user != null) {
            hospitalCollect.setHospitalId(user.getHospitalId());
        }
        List<HospitalCollect> list = hospitalCollectService.selectCollectinfoList(hospitalCollect);

        return getDataTable(list);
    }
//
    /**
     * 导出服务信息列表
     */
    @RequiresPermissions("register:collect:export")
    @Log(title = "服务信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HospitalCollect serviceinfo)
    {
        List<HospitalCollect> list = hospitalCollectService.selectCollectinfoList(serviceinfo);
        ExcelUtil<HospitalCollect> util = new ExcelUtil<HospitalCollect>(HospitalCollect.class);
        return util.exportExcel(list, "collect");
    }

    /**
     * 删除服务信息
     */
    @RequiresPermissions("register:collect:remove")
    @Log(title = "服务信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hospitalCollectService.deleteCollectinfoByIds(ids));
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
    @RequiresPermissions("register:collect:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HospitalCollect hospitalCollect)
    {
        return toAjax(hospitalCollectService.insertHospitalCollect(hospitalCollect));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HospitalCollect hospitalCollect = hospitalCollectService.selectHospitalCollectById(id);
        mmap.put("hospitalCollect", hospitalCollect);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("register:collect:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HospitalCollect hospitalCollect)
    {
        return toAjax(hospitalCollectService.updateHospitalCollect(hospitalCollect));
    }


}
