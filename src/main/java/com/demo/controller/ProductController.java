package com.demo.controller;

import com.demo.entity.Product;
import com.demo.entity.ProductCategory;
import com.demo.service.ProductCategoryService;
import com.demo.service.ProductService;
import com.demo.util.PropertiesFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public ModelAndView addProduct(@RequestParam(value = "file", required = true) MultipartFile file, Product product, HttpServletRequest request) throws IOException {
        String categoryId = request.getParameter("categoryId");
        if (categoryId == null) {
            return new ModelAndView("message","script","alert('请选择分类！');history.go(-1)");
        }
        ProductCategory productCategory = productCategoryService.findById(Integer.parseInt(categoryId));
        if (productCategory == null) {
            return new ModelAndView("message","script","alert('请选择分类！');history.go(-1)");
        }
        String uploadPath = new PropertiesFileReader("config.system.properties").getProperty("file_upload_path");
        String path = request.getSession().getServletContext().getRealPath(uploadPath);
        String filename = file.getOriginalFilename();
        File targetFile = new File(path, filename);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        file.transferTo(targetFile);
        product.setPicPath(uploadPath + "/" + filename);
        product.setUpdateTime(new Date());
        product.setCategory(productCategory);
        productService.add(product);
        return new ModelAndView("redirect:/");
    }
}
