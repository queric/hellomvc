package com.demo.controller;

import com.demo.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Queric on 2016/11/21.
 */
@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add(){
        ModelAndView mav=new ModelAndView("admin/product/edit");
        return mav;
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String addProduct(HttpServletRequest request) {
        Product product=new Product();
        product.setProductName(request.getParameter("productName"));
        return "redirect:/user";
    }
}
