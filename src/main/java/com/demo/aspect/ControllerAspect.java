package com.demo.aspect;

import com.demo.entity.Role;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Queric on 2016/12/13.
 */
@Aspect
@Component
public class ControllerAspect {
    @Pointcut("execution(public org.springframework.web.servlet.ModelAndView com.demo.controller.*.*(..))")
    private void pointCutMethod(){
    }

    @AfterReturning(pointcut = "pointCutMethod()", returning = "obj")
    public void doAfterReturning(Object obj) throws IOException{
        ModelAndView mav=(ModelAndView)obj;
        Properties properties=new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.system.properties");
        BufferedReader bf = new BufferedReader(new  InputStreamReader(inputStream, "UTF-8"));
        properties.load(bf);
        Iterator it=properties.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry=(Map.Entry)it.next();
            String key = entry.getKey().toString();
            Object value = entry.getValue();
            mav.addObject(key,value);
        }
    }
}
