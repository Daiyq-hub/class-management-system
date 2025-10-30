package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    // 学生管理页面
    @GetMapping("/students")
    public String studentManagement(HttpSession session, Model model) {
        // 检查登录状态和权限
        if (session.getAttribute("studentId") == null) {
            return "redirect:/login";
        }
        
        // 检查是否为管理员
        String role = (String) session.getAttribute("role");
        if (!"admin".equals(role)) {
            model.addAttribute("error", "您没有管理员权限权限！");
            return "redirect:/index";
        }
        
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "admin";
    }
    
    // 添加学生
    @PostMapping("/students/add")
    public String addStudent(@ModelAttribute Student student) {
        studentService.addStudent(student);
        return "redirect:/admin/students";
    }
    
    // 删除学生
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/admin/students";
    }
    
    // 反射机制演示
    @GetMapping("/reflection/demo")
    @ResponseBody
    public String reflectionDemo(@RequestParam String methodName) {
        try {
            // 使用反射机制调用方法
            StudentService service = new StudentService();
            return (String) service.getClass().getMethod(methodName).invoke(service);
        } catch (Exception e) {
            return "方法调用失败: " + e.getMessage();
        }
    }
}
