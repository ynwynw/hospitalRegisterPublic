package com.ruoyi.project.register.area.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.register.area.domain.Areainfo;
import com.ruoyi.project.register.area.service.IAreainfoService;
import com.ruoyi.project.register.registerinfo.domain.Registerinfo;
import com.ruoyi.project.register.registerinfo.service.IRegisterinfoService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ruoyi.common.utils.security.ShiroUtils.getSysUser;

/**
 * 服务信息Controller
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Controller
@RequestMapping("/register/area")
public class AreainfoController extends BaseController
{
    private String prefix = "register/area";

    @Autowired
    private IAreainfoService hospitalAreaService;

    @RequiresPermissions("register:area:view")
    @GetMapping()
    public String area()
    {
        return prefix + "/area";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("register:area:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Areainfo hospitalArea)
    {
        startPage();
        User user = getSysUser();
        if (user != null) {
            hospitalArea.setAreaId(user.getAreaId());
        }
        List<Areainfo> list = hospitalAreaService.selectHospitalAreaList(hospitalArea);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("register:area:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Areainfo hospitalArea)
    {
        List<Areainfo> list = hospitalAreaService.selectHospitalAreaList(hospitalArea);
        ExcelUtil<Areainfo> util = new ExcelUtil<Areainfo>(Areainfo.class);
        return util.exportExcel(list, "area");
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
    @RequiresPermissions("register:area:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Areainfo hospitalArea)
    {
        int i = hospitalAreaService.insertHospitalArea(hospitalArea);
        if (i==2){
            return new AjaxResult(AjaxResult.Type.ERROR,"已存在同名地区");
        }else {

            return toAjax(i);
        }
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{areaId}")
    public String edit(@PathVariable("areaId") Long areaId, ModelMap mmap)
    {
        Areainfo hospitalArea = hospitalAreaService.selectHospitalAreaById(areaId);
        mmap.put("hospitalArea", hospitalArea);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("register:area:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Areainfo hospitalArea)
    {
        return toAjax(hospitalAreaService.updateHospitalArea(hospitalArea));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("register:area:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hospitalAreaService.deleteHospitalAreaByIds(ids));
    }
}
