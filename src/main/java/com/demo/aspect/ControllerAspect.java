package com.demo.aspect;

import com.demo.util.PropertiesFileReader;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Queric on 2016/12/13.
 */
@Aspect
@Component
public class ControllerAspect {
    @Pointcut("execution(protected org.springframework.web.servlet.ModelAndView org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(..))")
    private void pointCutMethod(){
    }

    @AfterReturning(pointcut = "pointCutMethod()", returning = "obj")
    public void doAfterReturning(Object obj) throws IOException{
        ModelAndView mav=(ModelAndView)obj;
        String viewName=mav.getViewName();
        System.out.println(viewName);
        if (viewName.startsWith("redirect:")){return;}
        Properties properties=new PropertiesFileReader("config.system.properties").getProperties();
        Iterator it=properties.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry=(Map.Entry)it.next();
            String key = entry.getKey().toString();
            Object value = entry.getValue();
            mav.addObject(key,value);
        }
    }
}
