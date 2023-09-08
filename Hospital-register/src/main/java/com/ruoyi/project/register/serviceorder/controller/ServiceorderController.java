package com.ruoyi.project.register.serviceorder.controller;

import java.util.List;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.register.area.domain.Areainfo;
import com.ruoyi.project.register.area.service.IAreainfoService;
import com.ruoyi.project.register.area.service.impl.AreainfoServiceImpl;
import com.ruoyi.project.register.hospital.domain.Hospitalinfo;
import com.ruoyi.project.register.hospital.service.HospitalinfoService;
import com.ruoyi.project.system.role.domain.Role;
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
import com.ruoyi.project.register.serviceorder.domain.Serviceorder;
import com.ruoyi.project.register.serviceorder.service.IServiceorderService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 服务订单Controller
 *
 * @author yanqinglan
 * @date 2023/0/23
 */
@Controller
@RequestMapping("/register/serviceorder")
public class ServiceorderController extends BaseController {
    private String prefix = "register/serviceorder";

    @Autowired
    private IServiceorderService serviceorderService;


    @Autowired
    private IAreainfoService areainfoService;


    @Autowired
    private HospitalinfoService hospitalinfoService;

    @RequiresPermissions("register:serviceorder:view")
    @GetMapping()
    public String serviceorder() {
        return prefix + "/serviceorder";
    }

    /**
     * 查询服务订单列表
     */
    @RequiresPermissions("register:serviceorder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Serviceorder serviceorder) {

        User sysUser = ShiroUtils.getSysUser();
        List<Role> roles = sysUser.getRoles();
        startPage();
        if (CollUtil.isNotEmpty(roles)) {
            Role role = roles.stream().findFirst().orElse(new Role());
            String roleKey = role.getRoleKey();
            if (StrUtil.equals("doctor", roleKey)) {
                serviceorder.setUserId(sysUser.getUserId());
                List<Serviceorder> list = serviceorderService.selectServiceorderList(serviceorder);
                if (list.size()>0){
                    for (Serviceorder serviceorder1 : list) {
                        Areainfo areainfo = areainfoService.selectHospitalAreaById(serviceorder1.getAreaId());
                        Hospitalinfo hospitalinfo = hospitalinfoService.selectHospitalHospitalById(serviceorder1.getHospitalId());

                        if (areainfo != null){
                            serviceorder1.setAreaName(areainfo.getAreaName());
                        }
                        if (hospitalinfo != null){
                            serviceorder1.setHospitalName(hospitalinfo.getHospitalName());
                        }
                    }
                }
                return getDataTable(list);
            }
           else if (StrUtil.equals("hosadmin", roleKey)) {
                serviceorder.setHospitalId(sysUser.getHospitalId());
                List<Serviceorder> list = serviceorderService.selectServiceorderList(serviceorder);
                if (list.size()>0){
                    for (Serviceorder serviceorder1 : list) {
                        Areainfo areainfo = areainfoService.selectHospitalAreaById(serviceorder1.getAreaId());
                        Hospitalinfo hospitalinfo = hospitalinfoService.selectHospitalHospitalById(serviceorder1.getHospitalId());

                        if (areainfo != null){
                            serviceorder1.setAreaName(areainfo.getAreaName());
                        }
                        if (hospitalinfo != null){
                            serviceorder1.setHospitalName(hospitalinfo.getHospitalName());
                        }
                    }
                }
                return getDataTable(list);
            }
           else if (StrUtil.equals("sysadmin", roleKey) || StrUtil.equals("admin", roleKey)){
                List<Serviceorder> list = serviceorderService.selectServiceorderList(serviceorder);
                if (list.size()>0){
                    for (Serviceorder serviceorder1 : list) {
                        Areainfo areainfo = areainfoService.selectHospitalAreaById(serviceorder1.getAreaId());
                        Hospitalinfo hospitalinfo = hospitalinfoService.selectHospitalHospitalById(serviceorder1.getHospitalId());

                        if (areainfo != null){
                            serviceorder1.setAreaName(areainfo.getAreaName());
                        }
                        if (hospitalinfo != null){
                            serviceorder1.setHospitalName(hospitalinfo.getHospitalName());
                        }
                    }
                }
                return getDataTable(list);

            }


        }

//        User user = getSysUser();
//        if (user != null) {
//            if (!"00".equals(user.getUserType())) {
//                serviceorder.setHospitalId(user.getHospitalId());
//            }
//        }

//        List<Serviceorder> list = serviceorderService.selectServiceorderList(serviceorder);

        return null;

    }

    /**
     * 导出服务订单列表
     */
    @RequiresPermissions("register:serviceorder:export")
    @Log(title = "服务订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Serviceorder serviceorder) {
        List<Serviceorder> list = serviceorderService.selectServiceorderList(serviceorder);
        ExcelUtil<Serviceorder> util = new ExcelUtil<Serviceorder>(Serviceorder.class);
        return util.exportExcel(list, "serviceorder");
    }

    /**
     * 新增服务订单
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存服务订单
     */
    @RequiresPermissions("register:serviceorder:add")
    @Log(title = "服务订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Serviceorder serviceorder) {
        return toAjax(serviceorderService.insertServiceorder(serviceorder));
    }

    /**
     * 修改服务订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Serviceorder serviceorder = serviceorderService.selectServiceorderById(id);

        Areainfo areainfo = areainfoService.selectHospitalAreaById(serviceorder.getAreaId());
        Hospitalinfo hospitalinfo = hospitalinfoService.selectHospitalHospitalById(serviceorder.getHospitalId());

        if (areainfo != null){
            serviceorder.setAreaName(areainfo.getAreaName());
        }
        if (hospitalinfo != null){
            serviceorder.setHospitalName(hospitalinfo.getHospitalName());
        }
        mmap.put("serviceorder", serviceorder);
        return prefix + "/edit";
    }

    /**
     * 修改保存服务订单
     */
    @RequiresPermissions("register:serviceorder:edit")
    @Log(title = "服务订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Serviceorder serviceorder) {
        if (serviceorder.getServiceStatus().equals("2")) {
            serviceorder.setPayStatus("1");
        }
        return toAjax(serviceorderService.updateServiceorder(serviceorder));
    }

    /**
     * 删除服务订单
     */
    @RequiresPermissions("register:serviceorder:remove")
    @Log(title = "服务订单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(serviceorderService.deleteServiceorderByIds(ids));
    }
}
