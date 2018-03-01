package com.anlu.springmvc.controller;

import com.anlu.springmvc.model.User;
import com.anlu.springmvc.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/vali")
public class UserController {
        @RequestMapping(value="/registerForm",method=RequestMethod.GET)
    public String registerForm(Model model) {
        User user = new User();
        // model中添加属性user，值是user对象
        model.addAttribute("user",user);
        return "registerFormValidator";
    }

    @InitBinder
    public void initBinder(DataBinder binder) {
        // 设置验证的类为UserValidator
        binder.setValidator(new UserValidator());
    }

    @RequestMapping(value="/register",method= RequestMethod.POST)
    public String register(@Validated User user, Errors errors) {
        // 如果Errors对象有Field错误的时候，重新跳回注册页面，否则正常提交
        if (errors.hasFieldErrors())
            return "registerFormValidator";
        return "submit";
    }
}
