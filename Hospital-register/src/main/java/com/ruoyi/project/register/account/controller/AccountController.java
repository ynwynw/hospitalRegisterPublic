package com.ruoyi.project.register.account.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.register.account.domain.Account;
import com.ruoyi.project.register.account.service.IAccountService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 账户金额Controller
 *
 * @author yanqinglan
 * @date 2023/3/27
 */
@Controller
@RequestMapping("/register/account")
public class AccountController extends BaseController
{
    private String prefix = "register/account";

    @Autowired
    private IAccountService accountService;

    @RequiresPermissions("register:account:view")
    @GetMapping()
    public String account()
    {
        return prefix + "/account";
    }

    /**
     * 查询账户金额列表
     */
    @RequiresPermissions("register:account:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Account account)
    {
        startPage();
        List<Account> list = accountService.selectAccountList(account);
        return getDataTable(list);
    }

    /**
     * 导出账户金额列表
     */
    @RequiresPermissions("register:account:export")
    @Log(title = "账户金额", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Account account)
    {
        List<Account> list = accountService.selectAccountList(account);
        ExcelUtil<Account> util = new ExcelUtil<Account>(Account.class);
        return util.exportExcel(list, "账户金额");
    }

    /**
     * 新增账户金额
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存账户金额
     */
    @RequiresPermissions("register:account:add")
    @Log(title = "账户金额", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Account account)
    {
        return toAjax(accountService.insertAccount(account));
    }

    /**
     * 修改账户金额
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Account account = accountService.selectAccountById(id);
        mmap.put("account", account);
        return prefix + "/edit";
    }

    /**
     * 修改保存账户金额
     */
    @RequiresPermissions("register:account:edit")
    @Log(title = "账户金额", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Account account)
    {
        return toAjax(accountService.updateAccount(account));
    }

    /**
     * 删除账户金额
     */
    @RequiresPermissions("register:account:remove")
    @Log(title = "账户金额", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(accountService.deleteAccountByIds(ids));
    }





}
