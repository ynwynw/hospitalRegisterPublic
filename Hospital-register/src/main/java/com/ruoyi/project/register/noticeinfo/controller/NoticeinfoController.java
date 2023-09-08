package com.ruoyi.project.register.noticeinfo.controller;

import java.util.List;
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
import com.ruoyi.project.register.noticeinfo.domain.Noticeinfo;
import com.ruoyi.project.register.noticeinfo.service.INoticeinfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 公告信息Controller
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Controller
@RequestMapping("/register/noticeinfo")
public class NoticeinfoController extends BaseController
{
    private String prefix = "register/noticeinfo";

    @Autowired
    private INoticeinfoService noticeinfoService;

    @RequiresPermissions("register:noticeinfo:view")
    @GetMapping()
    public String noticeinfo()
    {
        return prefix + "/noticeinfo";
    }

    /**
     * 查询公告信息列表
     */
    @RequiresPermissions("register:noticeinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Noticeinfo noticeinfo)
    {
        startPage();
        List<Noticeinfo> list = noticeinfoService.selectNoticeinfoList(noticeinfo);
        return getDataTable(list);
    }

    /**
     * 导出公告信息列表
     */
    @RequiresPermissions("register:noticeinfo:export")
    @Log(title = "公告信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Noticeinfo noticeinfo)
    {
        List<Noticeinfo> list = noticeinfoService.selectNoticeinfoList(noticeinfo);
        ExcelUtil<Noticeinfo> util = new ExcelUtil<Noticeinfo>(Noticeinfo.class);
        return util.exportExcel(list, "noticeinfo");
    }

    /**
     * 新增公告信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存公告信息
     */
    @RequiresPermissions("register:noticeinfo:add")
    @Log(title = "资讯信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Noticeinfo noticeinfo)
    {
        return toAjax(noticeinfoService.insertNoticeinfo(noticeinfo));
    }

    /**
     * 修改公告信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Noticeinfo noticeinfo = noticeinfoService.selectNoticeinfoById(id);
        mmap.put("noticeinfo", noticeinfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存公告信息
     */
    @RequiresPermissions("register:noticeinfo:edit")
    @Log(title = "资讯信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Noticeinfo noticeinfo)
    {
        return toAjax(noticeinfoService.updateNoticeinfo(noticeinfo));
    }

    /**
     * 删除公告信息
     */
    @RequiresPermissions("register:noticeinfo:remove")
    @Log(title = "资讯信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(noticeinfoService.deleteNoticeinfoByIds(ids));
    }
}
