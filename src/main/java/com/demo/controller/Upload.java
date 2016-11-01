package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by Queric on 2016/8/2.
 */
@Controller
public class Upload {
    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String index(){
            return "upload";
    }
    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(MultipartFile file, HttpServletRequest request, ModelMap model){
        System.out.println("开始");
        String path = request.getSession().getServletContext().getRealPath("upload");

        String filename=file.getOriginalFilename();
        File targetFile=new File(path,filename);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(path);
        return "hiahiahia~";
    }
}
