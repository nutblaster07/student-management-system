package com.example.StudentManagement.controller;

import com.example.StudentManagement.model.Student;
import com.example.StudentManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    @Autowired
    private StudentService service;

    // Home Page (List all students)
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "index";  // Loads index.html
    }

    // Add Student Form Page
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";  // Loads add-student.html
    }

    // Add Student Action
    @PostMapping("/add")
    public String addStudent(Student student) {
        service.addStudent(student);
        return "redirect:/";
    }

    // Edit Student Form Page
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Student student = service.getStudentById(id);
        model.addAttribute("student", student);
        return "edit-student";  // Loads edit-student.html
    }

    // Update Student Action
    @PostMapping("/edit")
    public String updateStudent(Student student) {
        service.updateStudent(student);
        return "redirect:/";
    }

    // Delete Student
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
        return "redirect:/";
    }
}
