package io.kimmking.spring02;

import io.kimmking.aop.ISchool;
import io.kimmking.spring01.Student;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Data
public class School implements ISchool {
    /*
    @Autowire 和 @Resource 是两种常用的注入方法
    @Autowire 是默认按照类型来注入的
        - 注入时可使用 required 参数，值为 ture 和 false，表示我们是在启动的时候就要
          找到 Klass，把它先配置好；还是当我们调用 Klass 的某个属性方法的时候，再看它
          要不要装配，再拿到装配（懒加载）

    @Resource 是默认按名字来注入的
     */

    // Resource 
    @Autowired(required = true) //primary
    Klass class1;
    
    @Resource(name = "student100")
    Student student100;
    
    @Override
    public void ding(){
    
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);
        
    }
    
}
