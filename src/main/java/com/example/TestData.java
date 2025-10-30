package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestData implements CommandLineRunner {
    
    @Autowired
    private StudentService studentService;
    
    @Override
    public void run(String... args) throws Exception {
        // 添加测试数据
        Student admin = new Student();
        admin.setName("admin");
        admin.setStudentId("admin");
        admin.setGender("男");
        admin.setMajor("计算机科学与技术");
        admin.setClassNumber("计科1班");
        admin.setRole("admin");
        
        StudentService.addStudent(admin);
        
        Student student1 = new Student();
        student1.setName("张三");
        student1.setStudentId("2021001");
        student1.setGender("男");
        student1.setMajor("计算机科学与技术");
        student1.setClassNumber("计科1班");
        student1.setRole("user");
        
        studentService.addStudent(student1);
        
        Student student2 = new Student();
        student2.setName("李四");
        student2.setStudentId("2021002");
        student2.setGender("女");
        student2.setMajor("计算机科学与技术");
        student2.setClassNumber("计科1班");
        student2.setRole("user");
        
        studentService.addStudent(student2);
        
        System.out.println("测试数据添加完成！");
    }
}
