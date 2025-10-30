package com.example.demo.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String studentId;
    private String gender;
    private String major;
    private String classNumber;
    private String role; // "admin" or "user"
}
