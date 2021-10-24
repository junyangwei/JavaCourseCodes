package io.kimmking.spring02;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Aop2 {
    /*
    Spring 做 AOP 第二种方式：用全注解方式来配置（与第一种方式是等价的）
        - 定义了一个 AOP 切面的类 Aop2，开启了它上面的切面注解 @Aspect 表示整个类就是一个切面
        - 定义了一个方法 point (空的就可以了），在上面我们定义了一个切点（明确是 Klass 类相关的 dong 方法）
        - 再定义三个方法
            - 用 @Before 注解修饰的 before 方法
            - 用 @AfterReturning 注解修饰的 after 方法
            - 用 @Around 注解修饰的 around 方法
            - 每个注解里面需要给定一个 point()，表示对应的切点

        - 在配置文件中开启自动代理，它就会扫描到用注解配置的一些切面
            - 参考 resources/applicationContext.xml 第45行
     */
    
    @Pointcut(value="execution(* io.kimmking.*.Klass.*dong(..))")
    public void point(){
        
    }
    
    @Before(value="point()")
    public void before(){
        System.out.println("========>begin klass dong... //2");
    }
    
    @AfterReturning(value = "point()")
    public void after(){
        System.out.println("========>after klass dong... //4");
    }
    
    @Around("point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("========>around begin klass dong //1");
        joinPoint.proceed();
        System.out.println("========>around after klass dong //3");
        
    }
    
}
