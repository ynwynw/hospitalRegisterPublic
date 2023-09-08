package com.ruoyi.project.register.registerinfo.controller;

import java.util.List;

import com.ruoyi.project.register.registerinfo.domain.Registerinfo;
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
import com.ruoyi.project.register.registerinfo.service.IRegisterinfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 理发师信息Controller
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Controller
@RequestMapping("/register/registerinfo")
public class RegisterinfoController extends BaseController {
    private String prefix = "register/registerinfo";

    @Autowired
    private IRegisterinfoService registerinfoService;

    @RequiresPermissions("register:registerinfo:view")
    @GetMapping()
    public String registerinfo() {
        return prefix + "/registerinfo";
    }

    /**
     * 查询理发师信息列表
     */
    @RequiresPermissions("register:registerinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Registerinfo registerinfo) {
        startPage();
        List<Registerinfo> list = registerinfoService.selectRegisterinfoList(registerinfo);
        return getDataTable(list);
    }

    /**
     * 导出理发师信息列表
     */
    @RequiresPermissions("register:registerinfo:export")
    @Log(title = "理发师信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Registerinfo registerinfo) {
        List<Registerinfo> list = registerinfoService.selectRegisterinfoList(registerinfo);
        ExcelUtil<Registerinfo> util = new ExcelUtil<Registerinfo>(Registerinfo.class);
        return util.exportExcel(list, "registerinfo");
    }

    /**
     * 新增理发师信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存理发师信息
     */
    @RequiresPermissions("register:registerinfo:add")
    @Log(title = "理发师信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Registerinfo registerinfo) {
        int i = registerinfoService.insertRegisterinfo(registerinfo);

        if (i == 2) {
            return new AjaxResult(AjaxResult.Type.ERROR, "已有该科室");
        } else {
            return toAjax(i);
        }
    }

    /**
     * 修改理发师信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Registerinfo registerinfo = registerinfoService.selectRegisterinfoById(id);
        mmap.put("registerinfo", registerinfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存理发师信息
     */
    @RequiresPermissions("register:registerinfo:edit")
    @Log(title = "理发师信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Registerinfo registerinfo) {
        return toAjax(registerinfoService.updateRegisterinfo(registerinfo));
    }

    /**
     * 删除理发师信息
     */
    @RequiresPermissions("register:registerinfo:remove")
    @Log(title = "理发师信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(registerinfoService.deleteRegisterinfoByIds(ids));
    }


}
