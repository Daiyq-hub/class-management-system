package com.example.demo.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "duties")
public class Duty {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String studentName;
    private String studentId;
    private LocalDate dutyDate;
    private String dutyType; // "cleaning", "blackboard", "etc."
    private String status; // "completed", "pending"
}
