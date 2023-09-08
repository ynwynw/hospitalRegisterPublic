package com.ruoyi.project.common.arearegister.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.common.arearegister.domain.AreaRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务信息Controller
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@RestController
@RequestMapping("/AreaRegister")
public class AreaRegisterController extends BaseController {
//    @Autowired
//    private HospitalCollectService hospitalCollectService;
//
//    @PostMapping("/collect")
//    @ResponseBody
//    public AjaxResult updateCollect(AreaRegister hospitalCollect) {
//        int i = hospitalCollectService.updateCollect(hospitalCollect);
//        return AjaxResult.success();
//    }
}
