package com.ruoyi.project.register.address.controller;

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
import com.ruoyi.project.register.address.domain.Address;
import com.ruoyi.project.register.address.service.IAddressService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 地址管理Controller
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Controller
@RequestMapping("/register/address")
public class AddressController extends BaseController
{
    private String prefix = "register/address";

    @Autowired
    private IAddressService addressService;

    @RequiresPermissions("register:address:view")
    @GetMapping()
    public String address()
    {
        return prefix + "/address";
    }

    /**
     * 查询地址管理列表
     */
    @RequiresPermissions("register:address:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Address address)
    {
        startPage();
        List<Address> list = addressService.selectAddressList(address);
        return getDataTable(list);
    }

    /**
     * 导出地址管理列表
     */
    @RequiresPermissions("register:address:export")
    @Log(title = "地址管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Address address)
    {
        List<Address> list = addressService.selectAddressList(address);
        ExcelUtil<Address> util = new ExcelUtil<Address>(Address.class);
        return util.exportExcel(list, "address");
    }

    /**
     * 新增地址管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存地址管理
     */
    @RequiresPermissions("register:address:add")
    @Log(title = "地址管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Address address)
    {
        return toAjax(addressService.insertAddress(address));
    }

    /**
     * 修改地址管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Address address = addressService.selectAddressById(id);
        mmap.put("address", address);
        return prefix + "/edit";
    }

    /**
     * 修改保存地址管理
     */
    @RequiresPermissions("register:address:edit")
    @Log(title = "地址管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Address address)
    {
        return toAjax(addressService.updateAddress(address));
    }

    /**
     * 删除地址管理
     */
    @RequiresPermissions("register:address:remove")
    @Log(title = "地址管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(addressService.deleteAddressByIds(ids));
    }
}
