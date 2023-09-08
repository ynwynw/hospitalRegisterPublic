package com.ruoyi.project.system.user.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注册验证
 *
 * @author wj
 */
@Controller
public class ForgetPwdController extends BaseController {

    @Autowired
    private IUserService userService;

    @GetMapping("/forgetPwd")
    public String forgetPwd() {
        return "forgetPwd";
    }

    @PostMapping("/forgetPwd")
    @ResponseBody
    public AjaxResult ajaxForgetPwd(String username, String phonenumber, String password) {
        User user = userService.selectUserByLoginName(username);
        if (user != null && StringUtils.hasText(phonenumber)) {
            if (phonenumber.equals(user.getPhonenumber())) {
                user.setPassword(password);
                if (userService.resetUserPwd(user) > 0) {
                    return success();
                } else {
                    return error("服务器正忙，请稍后再试");
                }
            } else {
                return error("用户名和电话号码不匹配");
            }
        } else {
            return error("用户不存在");
        }
    }
}
