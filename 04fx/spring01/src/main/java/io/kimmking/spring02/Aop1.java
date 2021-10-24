package io.kimmking.spring02;

import org.aspectj.lang.ProceedingJoinPoint;

public class Aop1 {
    /*
    Spring 做 AOP 第一种方式：用代码和XML配置的方式来配置
        - 在类中写了三个方法，名叫 startTransaction, commitTransaction, around
        - 特别注意：around 的参数必须是 ProceedingJoinPoint
        - 在 Spring 配置文件中，将做 AOP 的类 Aop1 注册成一个 Bean
            - 参考 resources/applicationContext.xml 第40行
        - 然后我们就可以定义我们的 AOP
            - 参考 resources/applicationContext.xml 第47行
            - 先定义一个切点
            - 再定义一个切面
            - 然后把两个匹配到一块儿，就可以让切面的代码作用到所有切点上去
            - Tips：这里我们定义的切点是一个表达式，这个表达式可以用来匹配我们的包路径，类名，方法名，包括参数
                - 详情可以去看网上文章或者相关书籍
     */
    
    //前置通知
    public void startTransaction(){
        System.out.println("    ====>begin ding... "); //2
    }
        
    //后置通知
    public void commitTransaction(){
        System.out.println("    ====>finish ding... "); //4
    }
        
    //环绕通知
    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("    ====>around begin ding"); //1
        //调用process()方法才会真正的执行实际被代理的方法
        joinPoint.proceed();

        System.out.println("    ====>around finish ding"); //3
    }
        
}
