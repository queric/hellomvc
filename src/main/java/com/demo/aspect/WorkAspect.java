package com.demo.aspect;

import com.demo.util.PropertiesFileReader;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Queric on 2017/1/12.
 */
@Aspect
@Component
public class WorkAspect {
    @Pointcut("execution(* com.demo.test.TestWork.*(..))")
    private void pointCutMethod(){
    }

    @AfterReturning(pointcut = "pointCutMethod()", returning = "obj")
    public void doAfterReturning(Object obj) throws IOException {
        System.out.println("hit it");
    }
}
