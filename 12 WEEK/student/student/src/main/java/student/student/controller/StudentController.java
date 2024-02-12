package student.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import student.student.Student;
import student.student.service.StudentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public String addStudent(@ModelAttribute Student student){
        Object saveStudentData = studentService.saveStudent(student);
        return "/addStudentForm";
    }



}
