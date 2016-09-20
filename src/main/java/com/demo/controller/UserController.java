package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserService;
import com.demo.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by queric on 2016/9/8.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping( value = "/{page}",method = RequestMethod.GET)
    public ModelAndView showAllUsers(@PathVariable Integer page){
        PageBean pager=new PageBean(page,15);
        List<User> users=userService.findAll();
        ModelAndView mav=new ModelAndView("user");
        mav.addObject("users",users);
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getList(){
        return showAllUsers(1);
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String get(HttpServletRequest request){
        return "userAdd";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(HttpServletRequest request){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.save(user);
        return "redirect:/user";
    }


    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String deleteUser(@PathVariable Integer id){
        User user=new User();
        user.setId(id);
        userService.delete(user);
        return "redirect:/user";
    }
}
