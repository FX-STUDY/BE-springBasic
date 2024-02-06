package com.example.hello.controller;

import com.example.hello.entity.Student;
import com.example.hello.repository.StudentRepository;
import com.example.hello.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student/students")
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @GetMapping
    public String students(Model model) {
        List<Student> students = studentService.showAllStudents();
        model.addAttribute("students",students);
        return "/student/students";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        return "student/studentAddForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes) {
        Student savedStudent = studentService.addStudent(student);
        redirectAttributes.addAttribute("studentId",savedStudent.getStudentCode());
        redirectAttributes.addAttribute("status",true);
        return "redirect:/student/students/{studentCode}";
    }

    @GetMapping("/{studentCode}")
    public String student(@PathVariable long studentCode, Model model) {
        Student student = studentService.findStudent(studentCode);
        model.addAttribute("student",student);
        return "student/student";
    }

    @GetMapping("/{studentCode}/edit")
    public String editForm(@PathVariable Long studentCode, Model model) {
        Student student = studentService.findStudent(studentCode);
        model.addAttribute("student",student);
        return "student/studentEditForm";
    }

    @PostMapping("/{studentCode}/edit")
    public String edit(@PathVariable Long studentCode, @ModelAttribute Student student) {
        studentService.updateStudent(studentCode,student);
        return "redirect:/student/students/{studentCode}";
    }
}
