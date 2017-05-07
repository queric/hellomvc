package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Queric on 2016/7/14.
 */
@Controller
public class Login {
    @Autowired
   private UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String Index(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String DoLogin(Model model, String username, String password){
        //ModelAndView view=new ModelAndView("login");

        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        List<User> users=userService.findByExample(user);
        if (users.size()>0){
            return "test/test2";
        }
        else {
            model.addAttribute("script","<script>alert('用户名或密码错误！')</script>");
            return "test/test";
        }
    }
}
