package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Queric on 2016/11/22.
 */
@Controller
@RequestMapping("/bili")
public class BiliController {
    @RequestMapping(value = "/player")
    public ModelAndView getplayer(){
        ModelAndView mav=new ModelAndView("biliplayer");
        return mav;
    }
}
