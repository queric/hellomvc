package com.demo.controller;

import com.demo.entity.Product;
import com.demo.entity.ProductCategory;
import com.demo.service.ProductCategoryService;
import com.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Queric on 2016/11/21.
 */
@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "list")
    public ModelAndView list(){
        ModelAndView mav=new ModelAndView("admin/product/list");
        List<Product> list=productService.findAll();
        mav.addObject("productList",list);
        return mav;
    }
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add(){
        ModelAndView mav=new ModelAndView("admin/product/edit");
        List<ProductCategory> categories=productCategoryService.findAll();
        mav.addObject("categories",categories);
        return mav;
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public void addProduct(@RequestParam(value = "file", required = false) MultipartFile file, Product product, HttpServletRequest request) throws IOException {
        System.out.println(product.getProductName());
        System.out.println(product.getRecommendLevel());
        String path = request.getSession().getServletContext().getRealPath("upload");
        String filename=file.getOriginalFilename();
        File targetFile=new File(path,filename);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }
        file.transferTo(targetFile);
    }
}
