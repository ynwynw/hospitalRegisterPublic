package com.ruoyi.project.register.baseinfo.controller;

import java.util.List;

import com.ruoyi.project.common.arearegister.service.AreaRegisterService;
import com.ruoyi.project.register.area.domain.Areainfo;
import com.ruoyi.project.register.area.service.IAreainfoService;
import com.ruoyi.project.register.area.service.impl.AreainfoServiceImpl;
import com.ruoyi.project.register.baseinfo.domain.Baseinfo;
import com.ruoyi.project.register.baseinfo.service.IBaseinfoService;
import com.ruoyi.project.register.hospital.domain.Hospitalinfo;
import com.ruoyi.project.register.hospital.service.HospitalinfoService;
import com.ruoyi.project.register.registerinfo.domain.Registerinfo;
import com.ruoyi.project.system.user.domain.User;
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

import static com.ruoyi.common.utils.security.ShiroUtils.getSysUser;

/**
 * 茶之韵信息Controller
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Controller
@RequestMapping("/register/baseinfo")
public class BaseinfoController extends BaseController
{
    private String prefix = "register/baseinfo";

    @Autowired
    private IBaseinfoService baseinfoService;

    @Autowired
    private IAreainfoService areainfoService;


    @Autowired
    private HospitalinfoService hospitalinfoService;

    @RequiresPermissions("register:baseinfo:view")
    @GetMapping()
    public String baseinfo()
    {
        return prefix + "/baseinfo";
    }

    /**
     * 查询茶之韵信息列表
     */
    @RequiresPermissions("register:baseinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Baseinfo baseinfo)
    {
        startPage();
        User user = getSysUser();
        if (user != null) {
            baseinfo.setHospitalId(user.getHospitalId());
        }
        List<Baseinfo> list = baseinfoService.selectBaseinfoList(baseinfo);
        return getDataTable(list);
    }

    /**
     * 导出茶之韵信息列表
     */
    @RequiresPermissions("register:baseinfo:export")
    @Log(title = "茶之韵信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Baseinfo baseinfo)
    {
        List<Baseinfo> list = baseinfoService.selectBaseinfoList(baseinfo);
        ExcelUtil<Baseinfo> util = new ExcelUtil<Baseinfo>(Baseinfo.class);
        return util.exportExcel(list, "baseinfo");
    }

    /**
     * 新增茶之韵信息
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        List<Areainfo> areainfos = areainfoService.selectAll();

        mmap.put("areainfosList", areainfos);
        Hospitalinfo hospitalinfo = new Hospitalinfo();
        List<Hospitalinfo> hospitalinfo1 = hospitalinfoService.selectAll2(hospitalinfo);
        mmap.put("HospitalinfoList", hospitalinfo1);
        return prefix + "/add";
    }

    /**
     * 新增保存茶之韵信息
     */
    @RequiresPermissions("register:baseinfo:add")
    @Log(title = "茶之韵信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Baseinfo baseinfo)
    {
        return toAjax(baseinfoService.insertBaseinfo(baseinfo));
    }

    /**
     * 修改茶之韵信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Baseinfo baseinfo = baseinfoService.selectBaseinfoById(id);
        mmap.put("baseinfo", baseinfo);
        Hospitalinfo hospitalinfo = new Hospitalinfo();
        List<Hospitalinfo> hospitalinfo1 = hospitalinfoService.selectAll2(hospitalinfo);
        mmap.put("HospitalinfoList", hospitalinfo1);

        List<Areainfo> areainfos = areainfoService.selectAll();

        mmap.put("areainfosList", areainfos);

        User user = new User();
        return prefix + "/edit";
    }

    /**
     * 修改保存茶之韵信息
     */
    @RequiresPermissions("register:baseinfo:edit")
    @Log(title = "茶之韵信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Baseinfo baseinfo)
    {
        return toAjax(baseinfoService.updateBaseinfo(baseinfo));
    }

    /**
     * 删除茶之韵信息
     */
    @RequiresPermissions("register:baseinfo:remove")
    @Log(title = "茶之韵信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(baseinfoService.deleteBaseinfoByIds(ids));
    }
}
