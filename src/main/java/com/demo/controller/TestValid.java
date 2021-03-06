package com.demo.controller;

import com.demo.valid.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Queric on 2016/12/27.
 */
@Controller
@RequestMapping(value = "/test")
public class TestValid {
    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String getView(){
        return "/test/test";
    }
    @RequestMapping(value = "test",method = RequestMethod.POST)
    public String doPost(Model model, @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errorList = result.getAllErrors();
            for (ObjectError error : errorList) {
                model.addAttribute("script","alert('"+error.getDefaultMessage()+"')");
                break;
            }
            return "/test/test";
        }
        else {
            return "/";
        }
    }
}
