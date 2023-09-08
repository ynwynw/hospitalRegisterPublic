package com.ruoyi.project.register.serviceinfo.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.common.hospitalregister.domain.HospitalRegister;
import com.ruoyi.project.common.hospitalregister.service.HospitalRegisterService;
import com.ruoyi.project.register.area.domain.Areainfo;
import com.ruoyi.project.register.area.service.IAreainfoService;
import com.ruoyi.project.register.hospital.domain.Hospitalinfo;
import com.ruoyi.project.register.hospital.service.HospitalinfoService;
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

import static com.ruoyi.common.utils.security.ShiroUtils.getSysUser;

/**
 * 服务信息Controller
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Controller
@RequestMapping("/register/serviceinfo")
public class ServiceinfoController extends BaseController
{
    private String prefix = "register/serviceinfo";

    @Autowired
    private IServiceinfoService serviceinfoService;
    @Autowired
    private IRegisterinfoService registerinfoService;
    @Autowired
    private IUserService userService;


    @Autowired
    private IAreainfoService areainfoService;

    @Autowired
    private HospitalinfoService hospitalinfoService;


    @Autowired
    private HospitalRegisterService hospitalRegisterService;

    @RequiresPermissions("register:serviceinfo:view")
    @GetMapping()
    public String serviceinfo()
    {
        return prefix + "/serviceinfo";
    }

    /**
     * 查询服务信息列表
     */
    @RequiresPermissions("register:serviceinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Serviceinfo serviceinfo)
    {
        startPage();
        User user = getSysUser();
        if (user != null) {
            serviceinfo.setHospitalId(user.getHospitalId());
        }

        List<Serviceinfo> list = serviceinfoService.selectServiceinfoList(serviceinfo);
        return getDataTable(list);
    }

    /**
     * 导出服务信息列表
     */
    @RequiresPermissions("register:serviceinfo:export")
    @Log(title = "服务信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Serviceinfo serviceinfo)
    {
        List<Serviceinfo> list = serviceinfoService.selectServiceinfoList(serviceinfo);
        ExcelUtil<Serviceinfo> util = new ExcelUtil<Serviceinfo>(Serviceinfo.class);
        return util.exportExcel(list, "serviceinfo");
    }

    /**
     * 新增服务信息
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        List<Registerinfo> registerinfoList = registerinfoService.selectRegisterinfoList(new Registerinfo());
        mmap.put("registerinfoList", registerinfoList);

        List<Areainfo> areainfos = areainfoService.selectAll();

        mmap.put("areainfosList", areainfos);

//        ArrayList<Hospitalinfo> list = new ArrayList<>();

        List<Hospitalinfo> hospitalinfos = hospitalinfoService.selectAll2(new Hospitalinfo());
        mmap.put("HospitalinfoList", hospitalinfos);
        User user = new User();
        user.setUserType("02");
        List<User> userList = userService.selectUserList(user);
        mmap.put("userList", userList);
        return prefix + "/add";
    }

    /**
     * 新增保存服务信息
     */
    @RequiresPermissions("register:serviceinfo:add")
    @Log(title = "服务信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Serviceinfo serviceinfo)
    {
        serviceinfo.setUpdateTime(DateUtils.getNowDate());
        return toAjax(serviceinfoService.insertServiceinfo(serviceinfo));
    }

    /**
     * 修改服务信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Serviceinfo serviceinfo = serviceinfoService.selectServiceinfoById(id);
        mmap.put("serviceinfo", serviceinfo);
//        ArrayList<Hospitalinfo> list = new ArrayList<>();

//        HospitalRegister hospitalRegister = new HospitalRegister();
//        hospitalRegister.setRegisterId(serviceinfo.getRegisterId());
//        hospitalRegister.setHospitalId(serviceinfo.getHospitalId());
//        List<HospitalRegister> hospitalRegisters = hospitalRegisterService.selectHospitalRegister(hospitalRegister);
//        if(hospitalRegisters.size() > 0){
//            for (HospitalRegister register : hospitalRegisters) {
//                HospitalRegister hospitalRegister1 = new HospitalRegister();
//                hospitalRegister1.setAreaId(register.getAreaId());
//                List<HospitalRegister> hospitalRegisters1 = hospitalRegisterService.selectHospitalRegister(hospitalRegister1);
//                for (HospitalRegister hospitalRegister2 : hospitalRegisters1) {

//                    hospitalinfo.setHospitalId(hospitalRegister2.getHospitalId());

//                }
//
//            }
//        }
        Hospitalinfo hospitalinfo = new Hospitalinfo();
//        hospitalinfo.setHospitalId(serviceinfo.getHospitalId());
        List<Hospitalinfo> hospitalinfo1 = hospitalinfoService.selectAll2(hospitalinfo);
        mmap.put("HospitalinfoList", hospitalinfo1);

        List<Areainfo> areainfos = areainfoService.selectAll();

        mmap.put("areainfosList", areainfos);
        List<Registerinfo> registerinfoList = registerinfoService.selectRegisterinfoList(new Registerinfo());
        mmap.put("registerinfoList", registerinfoList);
        User user = new User();
        user.setUserType("02");
        List<User> userList = userService.selectUserList(user);
        mmap.put("userList", userList);
        return prefix + "/edit";
    }

    /**
     * 修改保存服务信息
     */
    @RequiresPermissions("register:serviceinfo:edit")
    @Log(title = "服务信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Serviceinfo serviceinfo)
    {
        return toAjax(serviceinfoService.updateServiceinfo(serviceinfo));
    }

    /**
     * 删除服务信息
     */
    @RequiresPermissions("register:serviceinfo:remove")
    @Log(title = "服务信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(serviceinfoService.deleteServiceinfoByIds(ids));
    }
}
