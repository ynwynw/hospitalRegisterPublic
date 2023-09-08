package com.ruoyi.project.register.hospital.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.register.hospital.domain.Hospitalinfo;
import com.ruoyi.project.register.hospital.service.HospitalinfoService;
import com.ruoyi.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ruoyi.common.utils.security.ShiroUtils.getSysUser;

/**
 * 公告信息Controller
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Controller
@RequestMapping("/register/hospital")
public class HospitalinfoController extends BaseController
{
    private String prefix = "register/hospital";

    @Autowired
    private HospitalinfoService hospitalHospitalService;

    @RequiresPermissions("register:hospital:view")
    @GetMapping()
    public String hospital()
    {
        return prefix + "/hospital";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("register:hospital:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Hospitalinfo hospitalHospital)
    {
        startPage();
        User user = getSysUser();
        if (user != null) {
            hospitalHospital.setHospitalId(user.getHospitalId());
        }
        List<Hospitalinfo> list = hospitalHospitalService.selectHospitalHospitalList(hospitalHospital);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("register:hospital:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Hospitalinfo hospitalHospital)
    {
        List<Hospitalinfo> list = hospitalHospitalService.selectHospitalHospitalList(hospitalHospital);
        ExcelUtil<Hospitalinfo> util = new ExcelUtil<Hospitalinfo>(Hospitalinfo.class);
        return util.exportExcel(list, "hospital");
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
    @RequiresPermissions("register:hospital:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Hospitalinfo hospitalHospital)
    {
        int i = hospitalHospitalService.insertHospitalHospital(hospitalHospital);
        if (i == 2){
            return new AjaxResult(AjaxResult.Type.ERROR,"已存在同名医院");
        }else {
            return toAjax(i);
        }


    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{hospitalId}")
    public String edit(@PathVariable("hospitalId") Long hospitalId, ModelMap mmap)
    {
        Hospitalinfo hospitalHospital = hospitalHospitalService.selectHospitalHospitalById(hospitalId);
        mmap.put("hospitalHospital", hospitalHospital);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("register:hospital:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Hospitalinfo hospitalHospital)
    {
        return toAjax(hospitalHospitalService.updateHospitalHospital(hospitalHospital));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("register:hospital:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hospitalHospitalService.deleteHospitalHospitalByIds(ids));
    }
}
