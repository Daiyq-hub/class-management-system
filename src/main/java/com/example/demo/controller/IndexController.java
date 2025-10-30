package com.example.demo.controller;

import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.bind.bind.annotation.PostMapping;
import javax.servlet.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    
    @Autowired
    private StudentService studentService;
    
    // 登录页面
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    // 登录验证
    @PostMapping("/login")
    public String login(@RequestParam String studentId, @RequestParam String name, 
                       Model model, HttpSession session) {
        if (studentService.validateUser(studentId, name)) {
            String role = studentService.getUserRole(studentId);
            session.setAttribute("studentId", studentId);
            session.setAttribute("name", name);
            session.setAttribute("role", role);
            return "redirect:/index";
        } else {
            model.addAttribute("error", "学号或姓名错误，请重试！");
            return "login";
        }
    }
    
    // 主页
    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        if (session.getAttribute("studentId") == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("role", session.getAttribute("role"));
        return "index";
    }
    
    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
    // 反射机制测试页面
    @GetMapping("/reflection")
    public String reflection(HttpSession session) {
        if (session.getAttribute("studentId") == null) {
            return "redirect:/login";
        }
        return "reflection";
    }
    
    // 映射功能测试页面
    @GetMapping("/map")
    public String map(HttpSession session) {
        if (session.getAttribute("studentId") == null) {
            return "redirect:/login";
        }
        return "map";
    }
    
    // 聊天室页面
    @GetMapping("/chat")
    public String chat(HttpSession session, Model model) {
        if (session.getAttribute("studentId") == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("username", session.getAttribute("name"));
        return "chat";
    }
}
