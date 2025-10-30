package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    // 获取所有学生
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    // 根据ID获取学生
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
    
    // 添加学生
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
    
    // 更新学生
    public Student updateStudent(Long id, Student studentDetails) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(studentDetails.getName());
                    student.setStudentId(studentDetails.getStudentId());
                    student.setGender(studentDetails.getGender());
                    student.setMajor(studentDetails.getMajor());
                    student.setClassNumber(studentDetails.getClassNumber());
                    student.setRole(studentDetails.getRole());
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
    }
    
    // 删除学生
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    
    // 用户登录验证
    public boolean validateUser(String studentId, String name) {
        return studentRepository.findByStudentIdAndName(studentId, name).isPresent();
    }
    
    // 获取用户角色
    public String getUserRole(String studentId) {
        return studentRepository.findByStudentId(studentId)
                .map(Student::getRole)
                .orElse("user"); // 默认普通用户
    }
}
